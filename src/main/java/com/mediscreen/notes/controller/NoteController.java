package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
