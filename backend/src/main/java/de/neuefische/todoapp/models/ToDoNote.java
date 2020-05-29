package de.neuefische.todoapp.models;

import de.neuefische.todoapp.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoNote {

    private String id;
    private String description;
    private Status status;
}
