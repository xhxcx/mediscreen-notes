package com.mediscreen.notes.service;

import com.mediscreen.notes.model.NoteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    List<NoteDto> getAll();
    List<NoteDto> getNotesByPatientId(int patientId);
    NoteDto findNote(String noteId);
    NoteDto saveNote(NoteDto noteToSave);
}
