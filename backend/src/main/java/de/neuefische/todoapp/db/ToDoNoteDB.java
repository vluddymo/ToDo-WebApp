package de.neuefische.todoapp.db;

import de.neuefische.todoapp.data.Description;
import de.neuefische.todoapp.data.Stati;
import de.neuefische.todoapp.enums.Status;
import de.neuefische.todoapp.models.ToDoNote;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ToDoNoteDB {

    List<ToDoNote> toDoNoteList = new ArrayList<>();


    public List<ToDoNote> getAllNotes() {
        return toDoNoteList;
    }

    public void addNoteInBackend(ToDoNote note) {
        toDoNoteList.add(note);
    }

    public ToDoNote addNoteToDb(Description description) {
        String uuid = UUID.randomUUID().toString();
        String message = description.getDescription();
        ToDoNote newNote = new ToDoNote(uuid, message, Status.OPEN);
        toDoNoteList.add(newNote);
        return newNote;
    }


    public void removeNoteById(String id) {
        toDoNoteList.removeIf(note -> note.getId().equals(id));
    }


    public void clearDb() {
        toDoNoteList.clear();
    }

    public ToDoNote setStatusOfNote(String id, Stati status) {
        Status newStatus = status.getStatus();
        for (ToDoNote note : toDoNoteList) {
            if (note.getId().equals(id)) {
                note.setStatus(newStatus);
                return note;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order does not exist");
    }
}
