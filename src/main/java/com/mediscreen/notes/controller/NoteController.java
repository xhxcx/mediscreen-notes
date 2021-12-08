package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @ApiOperation(value = "get all notes as a list")
    @GetMapping("/")
    public ResponseEntity<List<NoteDto>> getAll(){
        LOGGER.info("GET / ");
        return new ResponseEntity<>(noteService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "get all notes for the given patient based on its id as path variable")
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<NoteDto>> getNotesForPatientId(@PathVariable("id") int id){
        LOGGER.info("GET /patient/{id} patientId=" + id);
        return new ResponseEntity<>(noteService.getNotesByPatientId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get a note by its id")
    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> findNote(@PathVariable("id") String id){
        LOGGER.info("GET /id noteId=" + id);
        NoteDto existingNote = noteService.findNote(id);
        return existingNote != null ? new ResponseEntity<>(existingNote, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "create a new note")
    @PostMapping("/")
    public ResponseEntity<NoteDto> saveNote(@Valid @RequestBody NoteDto note){
        LOGGER.info("POST / newNote= " + note);
        NoteDto savedNote = noteService.saveNote(note);
        return savedNote != null ? new ResponseEntity<>(savedNote, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Update an existing note")
    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@Valid @RequestBody NoteDto noteToUpdate){
        LOGGER.info("PUT / noteToUpdate id= " + noteToUpdate.getId());
        return new ResponseEntity<>(noteService.saveNote(noteToUpdate), HttpStatus.OK);
    }
}
