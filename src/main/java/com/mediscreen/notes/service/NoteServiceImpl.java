package com.mediscreen.notes.service;

import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.model.NoteMapper;
import com.mediscreen.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<NoteDto> getAll() {
        return NoteMapper.INSTANCE.mapToDtoList(noteRepository.findAll());
    }

    @Override
    public List<NoteDto> getNotesByPatientId(int patientId) {
        return NoteMapper.INSTANCE.mapToDtoList(noteRepository.findAllByPatientId(patientId));
    }

    @Override
    public NoteDto findNote(String noteId) {
        return NoteMapper.INSTANCE.mapToDTO(noteRepository.findById(noteId).orElse(null));
    }

    @Override
    public NoteDto saveNote(NoteDto noteToSave) {
        Note note = NoteMapper.INSTANCE.mapToEntity(noteToSave);
        return NoteMapper.INSTANCE.mapToDTO(noteRepository.save(note));
    }
}
