package de.neuefische.todoapp.controller;


import de.neuefische.todoapp.data.Description;
import de.neuefische.todoapp.data.Stati;
import de.neuefische.todoapp.enums.Status;
import de.neuefische.todoapp.models.ToDoNote;
import de.neuefische.todoapp.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoNote> getToDos() {
        return toDoService.getAllNotes();
    }

    @PutMapping
    public ToDoNote addANote(@RequestBody Description description) {
        return toDoService.addANote(description);
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable String id) {
        toDoService.deleteNotesById(id);
    }

    @PutMapping("{id}/status")
    public ToDoNote updateStatus(@PathVariable String id, @RequestBody Stati status) {
        return toDoService.setStatus(id, status);
    }

}
