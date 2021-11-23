package com.mediscreen.service;

import com.mediscreen.model.NoteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
    List<NoteDto> getAll();
    List<NoteDto> getNotesByPatientId(int patientId);
}
