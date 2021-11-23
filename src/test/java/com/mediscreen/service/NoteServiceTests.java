package com.mediscreen.service;

import com.mediscreen.model.Note;
import com.mediscreen.model.NoteDto;
import com.mediscreen.repository.NoteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class NoteServiceTests {
    @Mock
    private NoteRepository noteRepositoryMock;

    @InjectMocks
    private NoteServiceImpl noteService;

    private static final Note note = new Note();

    @BeforeAll
    static void setUp(){
        note.setId("noteId");
        note.setPatientId(1);
        note.setNote("medical comments");
    }

    @Test
    public void getAllTest(){
        Mockito.when(noteRepositoryMock.findAll()).thenReturn(Collections.singletonList(note));

        List<NoteDto> result = noteService.getAll();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("noteId", result.get(0).getId());
    }

    @Test
    public void getNotesByPatientIdTest(){
        Mockito.when(noteRepositoryMock.findAllByPatientId(1)).thenReturn(Collections.singletonList(note));

        List<NoteDto> result = noteService.getNotesByPatientId(1);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("noteId", result.get(0).getId());
    }
}
