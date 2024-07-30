package com.example.newpackage.Controller;

import com.example.newpackage.entity.AppUser;
import com.example.newpackage.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping()
    public List<AppUser> getAllAppUsers() {
        return appUserService.getAllAppUsers();
    }

    @PostMapping
    public void createAppUser(@RequestBody AppUser appUser) {
        appUserService.saveAppUser(appUser);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateAppUser(@RequestBody AppUser appUser, @PathVariable String userName) {
        AppUser appUserInDb = appUserService.findByUserName(userName);
        if (appUserInDb != null) {
            appUserInDb.setUserName(appUser.getUserName());
            appUserInDb.setUserPassword(appUser.getUserPassword());
            appUserService.saveAppUser(appUserInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
