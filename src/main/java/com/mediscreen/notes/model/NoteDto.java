package com.mediscreen.notes.model;

import lombok.Data;

@Data
public class NoteDto {
    private String id;
    private Integer patientId;
    private String note;
}
