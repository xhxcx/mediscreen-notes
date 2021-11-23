package com.mediscreen.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notes")
public class Note {

    @Id
    private String id;
    private Integer patientId;
    private String note;

    public Note(){}

    public Note(Integer patientId, String note){
        this.patientId = patientId;
        this.note = note;
    }

}
