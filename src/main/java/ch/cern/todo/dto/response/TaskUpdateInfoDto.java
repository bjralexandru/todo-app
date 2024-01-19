package ch.cern.todo.dto.response;

import ch.cern.todo.entity.TaskCategories;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class TaskUpdateInfoDto {

    private String taskName;
    private String taskDescription;
    private LocalDate deadline;
    private String categoryName;

    public TaskUpdateInfoDto(String taskName, String taskDescription,LocalDate deadline, String categoryName) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.categoryName = categoryName;
    }
}
