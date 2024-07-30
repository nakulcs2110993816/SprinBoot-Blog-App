package com.example.newpackage.service;

import com.example.newpackage.entity.DiaryEntry;
import com.example.newpackage.entity.AppUser;
import com.example.newpackage.repository.EntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryEntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private AppUserService appUserService;

    public void saveDiaryEntry(DiaryEntry diaryEntry, String userName) {
        AppUser appUser = appUserService.findByUserName(userName);
        diaryEntry.setEntryDate(LocalDateTime.now());
        DiaryEntry savedEntry = entryRepository.save(diaryEntry);
        appUser.getDiaryEntries().add(savedEntry);
        appUserService.saveAppUser(appUser);
    }

    public List<DiaryEntry> getAllDiaryEntries() {
        return entryRepository.findAll();
    }

    public Optional<DiaryEntry> findDiaryEntryById(ObjectId entryId) {
        return entryRepository.findById(entryId);
    }

    public void deleteDiaryEntryById(ObjectId entryId, String userName) {
        AppUser appUser = appUserService.findByUserName(userName);
        appUser.getDiaryEntries().removeIf(entry -> entry.getEntryId().equals(entryId));
        appUserService.saveAppUser(appUser);
        entryRepository.deleteById(entryId);
    }
}
