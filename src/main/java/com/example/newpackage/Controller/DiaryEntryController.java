package com.example.newpackage.Controller;

import com.example.newpackage.entity.DiaryEntry;
import com.example.newpackage.entity.AppUser;
import com.example.newpackage.service.DiaryEntryService;
import com.example.newpackage.service.AppUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diary")
public class DiaryEntryController {

    @Autowired
    private DiaryEntryService diaryEntryService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/{userName}")
    public List<DiaryEntry> getAllDiaryEntriesOfUser(@PathVariable String userName) {
        AppUser appUser = appUserService.findByUserName(userName);
        return appUser.getDiaryEntries();
    }

    @PostMapping("/{userName}")
    public DiaryEntry createDiaryEntry(@RequestBody DiaryEntry diaryEntry, @PathVariable String userName) {
        diaryEntryService.saveDiaryEntry(diaryEntry, userName);
        return diaryEntry;
    }

    @GetMapping("/id/{entryId}")
    public ResponseEntity<DiaryEntry> getDiaryEntryById(@PathVariable ObjectId entryId) {
        Optional<DiaryEntry> diaryEntry = diaryEntryService.findDiaryEntryById(entryId);
        return diaryEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/id/{userName}/{entryId}")
    public ResponseEntity<?> deleteDiaryEntryById(@PathVariable ObjectId entryId, @PathVariable String userName) {
        diaryEntryService.deleteDiaryEntryById(entryId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{entryId}")
    public DiaryEntry updateDiaryEntryById(@PathVariable ObjectId entryId, @RequestBody DiaryEntry entry) {
        DiaryEntry existingEntry = diaryEntryService.findDiaryEntryById(entryId).orElse(null);
        if (existingEntry != null) {
            existingEntry.setEntryTitle(!entry.getEntryTitle().isEmpty() ? entry.getEntryTitle() : existingEntry.getEntryTitle());
            existingEntry.setEntryContent(entry.getEntryContent() != null && !entry.getEntryContent().isEmpty() ? entry.getEntryContent() : existingEntry.getEntryContent());
            diaryEntryService.saveDiaryEntry(existingEntry, existingEntry.getEntryTitle());  // save updated entry
        }
        return existingEntry;
    }
}
