package com.example.newpackage.repository;

import com.example.newpackage.entity.AppUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, ObjectId> {
    AppUser findByUserName(String userName);
}
