package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.data.Description;
import de.neuefische.todoapp.db.ToDoNoteDB;
import de.neuefische.todoapp.enums.Status;
import de.neuefische.todoapp.models.ToDoNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static de.neuefische.todoapp.enums.Status.OPEN;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ToDoNoteDB toDoNoteDB;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void resetDatabase() {
        toDoNoteDB.clearDb();
    }

    @Test
    public void addingAnItemToDatabaseShouldDisplayItemOnFrontend() {

        //GIVEN
        toDoNoteDB.addNoteInBackend(new ToDoNote("34","testy test", OPEN));

        //WHEN
        ResponseEntity<ToDoNote[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo", ToDoNote[].class);
        HttpStatus statusCode = response.getStatusCode();
        ToDoNote[] notes = response.getBody();

        //THEN
        assertEquals("testy test", notes[0].getDescription());
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(1, notes.length);

    }

    @Test
    public void addingANoteInFrontendShouldPlaceThisItemInDatabase() {

        //GIVEN
        Description newNote = new Description("I need to test my Putmapping");
        HttpEntity<Description> requestEntity = new HttpEntity<>(newNote);

        //WHEN
        ResponseEntity<ToDoNote> response = restTemplate.exchange("http://localhost:" + port + "/api/todo",HttpMethod.PUT, requestEntity, ToDoNote.class);
        HttpStatus statusCode = response.getStatusCode();
        ToDoNote notes = response.getBody();

        //THEN
        assertEquals(1, toDoNoteDB.getAllNotes().size());
        assertEquals(OPEN, notes.getStatus());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(toDoNoteDB.getAllNotes().contains(notes));
        assertEquals("I need to test my Putmapping", notes.getDescription());
    }


}