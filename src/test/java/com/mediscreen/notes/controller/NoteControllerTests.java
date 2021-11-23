package com.mediscreen.notes.controller;

import com.mediscreen.notes.model.NoteDto;
import com.mediscreen.notes.service.NoteService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NoteControllerTests {

    @MockBean
    private NoteService noteServiceMock;

    @Autowired
    private MockMvc mockMvc;

    private static final NoteDto note = new NoteDto();

    @BeforeAll
    static void setUp(){
        note.setId("noteId");
        note.setPatientId(1);
        note.setNote("medical comments");
    }

    @Test
    public void getAllShouldReturn200AndAListOfNoteDTO() throws Exception {
        Mockito.when(noteServiceMock.getAll()).thenReturn(Collections.singletonList(note));

        String expectedContent = "{\"id\":\"noteId\",\"patientId\":1,\"note\":\"medical comments\"}";

        mockMvc.perform(get("/"))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedContent));
                });
    }

    @Test
    public void getNotesForPatientIdShouldReturn200AndAListOfNoteDTO() throws Exception {
        Mockito.when(noteServiceMock.getNotesByPatientId(1)).thenReturn(Collections.singletonList(note));

        String expectedContent = "{\"id\":\"noteId\",\"patientId\":1,\"note\":\"medical comments\"}";

        mockMvc.perform(get("/patient/1"))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedContent));
                });
    }

    @Test
    public void saveNoteShouldReturn201AndNewNoteDTO() throws Exception {
        Mockito.when(noteServiceMock.saveNote(any(NoteDto.class))).thenReturn(note);

        String newNoteBody = "{\"patientId\":1,\"note\":\"medical comments\"}";
        String expectedResult = "{\"id\":\"noteId\",\"patientId\":1,\"note\":\"medical comments\"}";

        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(newNoteBody))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().contains(expectedResult));
                });
    }

    @Test
    public void saveNoteShouldReturn400WhenNoteDtoNotValid() throws Exception {
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content("{\"validJson\":\"but not a valid note\"}"))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
                });
    }
}
