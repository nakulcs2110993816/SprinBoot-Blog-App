package com.example.newpackage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class DiaryEntry {

    @Id
    private ObjectId entryId;

    @NonNull
    private String entryTitle;

    private String entryContent;

    private LocalDateTime entryDate;
}
