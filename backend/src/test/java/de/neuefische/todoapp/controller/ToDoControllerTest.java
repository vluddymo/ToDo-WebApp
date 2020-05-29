package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.db.ToDoNoteDB;
import de.neuefische.todoapp.enums.Status;
import de.neuefische.todoapp.models.ToDoNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        toDoNoteDB.addNoteInBackend(new ToDoNote("34","testy test", Status.OPEN));


        //WHEN
        ResponseEntity<ToDoNote[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo", ToDoNote[].class);
        HttpStatus statusCode = response.getStatusCode();
        ToDoNote[] notes = response.getBody();

        //THEN
        assertTrue(notes[0].getDescription().equals("testy test"));
        assertEquals(HttpStatus.OK, statusCode);

    }
}