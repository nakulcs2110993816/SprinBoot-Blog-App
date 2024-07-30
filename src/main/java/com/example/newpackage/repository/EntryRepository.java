package com.example.newpackage.repository;

import com.example.newpackage.entity.DiaryEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<DiaryEntry, ObjectId> {

}
