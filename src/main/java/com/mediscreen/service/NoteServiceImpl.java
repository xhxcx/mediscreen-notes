package com.mediscreen.service;

import com.mediscreen.model.NoteDto;
import com.mediscreen.model.NoteMapper;
import com.mediscreen.repository.NoteRepository;
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
}
