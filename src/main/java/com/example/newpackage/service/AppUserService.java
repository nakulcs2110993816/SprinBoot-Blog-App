package com.example.newpackage.service;

import com.example.newpackage.entity.AppUser;
import com.example.newpackage.repository.AppUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public void saveAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findById(ObjectId userId) {
        return appUserRepository.findById(userId);
    }

    public void deleteById(ObjectId userId) {
        appUserRepository.deleteById(userId);
    }

    public AppUser findByUserName(String userName) {
        return appUserRepository.findByUserName(userName);
    }
}
