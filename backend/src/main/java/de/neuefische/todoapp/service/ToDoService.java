package de.neuefische.todoapp.service;

import de.neuefische.todoapp.data.Description;
import de.neuefische.todoapp.data.Stati;
import de.neuefische.todoapp.db.ToDoNoteDB;
import de.neuefische.todoapp.models.ToDoNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    private final ToDoNoteDB toDoNotes;

    @Autowired
    public ToDoService(ToDoNoteDB toDoNotes) {
        this.toDoNotes = toDoNotes;
    }

    public List<ToDoNote> getAllNotes() {
        return toDoNotes.getAllNotes();
    }

    public void deleteNotesById(String id) {
        toDoNotes.removeNoteById(id);
    }

    public ToDoNote addANote(Description description) {
        return toDoNotes.addNoteToDb(description);
    }

    public ToDoNote setStatus(String id, Stati status) {
        return toDoNotes.setStatusOfNote(id, status);
    }
}
