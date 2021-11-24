package com.mediscreen.notes.service;

import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.model.NoteMapper;
import com.mediscreen.notes.repository.NoteRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void saveNoteTest(){
        Note noteToSave = new Note();
        noteToSave.setPatientId(1);
        noteToSave.setNote("medical comments");

        Mockito.when(noteRepositoryMock.save(noteToSave)).thenReturn(note);

        NoteDto result = noteService.saveNote(NoteMapper.INSTANCE.mapToDTO(noteToSave));

        Assert.assertEquals(note.getId(), result.getId());
    }

    @Test
    public void findNoteOKTest(){
        Mockito.when(noteRepositoryMock.findById("noteId")).thenReturn(java.util.Optional.of(note));

        Assert.assertEquals(1, (int) noteService.findNote("noteId").getPatientId());
    }

    @Test
    public void findNoteNotFoundTest(){
        Mockito.when(noteRepositoryMock.findById("badId")).thenReturn(Optional.empty());

        Assert.assertNull(noteService.findNote("badId"));
    }
}
