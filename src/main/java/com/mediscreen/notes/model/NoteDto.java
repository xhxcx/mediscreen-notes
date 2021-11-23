package com.mediscreen.notes.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoteDto {
    private String id;

    @NotNull
    private Integer patientId;

    @NotNull
    private String note;
}
