package de.neuefische.todoapp.data;

import de.neuefische.todoapp.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stati {

    private Status status;
}
