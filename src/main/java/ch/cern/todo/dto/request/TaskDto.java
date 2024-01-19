package ch.cern.todo.dto.request;

import ch.cern.todo.entity.TaskCategories;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class TaskDto {
    @Size(max=100)
    private String taskName;

    @Size(max=500)
    private String taskDescription;

    private LocalDate deadline;

    private String categoryName;

    public TaskDto(String taskName, String taskDescription,String deadlineString, String categoryName) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = LocalDate.parse(deadlineString);
        this.categoryName = categoryName;
    }

}
