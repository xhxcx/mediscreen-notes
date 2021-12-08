package com.mediscreen.notes.integration;


import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.repository.NoteRepository;
import com.mediscreen.notes.service.NoteService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class NoteServiceIT {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    @BeforeEach
    public void cleanUp(){
        mongoTemplate.dropCollection(Note.class);
    }

    @Test
    public void saveNoteIT(){
        NoteDto newNote = new NoteDto();
        newNote.setPatientId(1);
        newNote.setNote("integration test note");

        noteService.saveNote(newNote);

        Assert.assertEquals(1, noteRepository.findAll().size());
    }

}
