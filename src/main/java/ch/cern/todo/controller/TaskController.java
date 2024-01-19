package ch.cern.todo.controller;

import ch.cern.todo.dto.request.TaskDto;

import ch.cern.todo.dto.response.TaskUpdateInfoDto;
import ch.cern.todo.entity.Tasks;
import ch.cern.todo.exceptions.TaskCategoryNotFoundException;
import ch.cern.todo.exceptions.TaskNotFoundException;
import ch.cern.todo.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PutMapping("/new_task")
    public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDto taskDto) throws TaskCategoryNotFoundException {
        try {
            String createdTaskDto = taskService.addTask(taskDto);
            return ResponseEntity.status(201).body(createdTaskDto);
        } catch (TaskCategoryNotFoundException ex) {
            return ResponseEntity.status(400).body("Category " + taskDto.getCategoryName()+" not found!");
        }
    }

    @PutMapping("/update_task/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) throws TaskNotFoundException {
        Optional<Tasks> taskToUpdate = taskService.findByTaskId(taskId);
        taskService.updateTask(taskToUpdate, taskDto);
        Optional<Tasks> taskAfterUpdate = taskService.findByTaskId(taskId);
        TaskUpdateInfoDto taskUpdateInfoDto = new TaskUpdateInfoDto(taskAfterUpdate.get().getTaskName(),
                taskAfterUpdate.get().getTaskDescription(), taskAfterUpdate.get().getDeadline(),
                taskAfterUpdate.get().getCategory());
        return ResponseEntity.status(201).body(taskUpdateInfoDto);
    }

    @GetMapping("/all")
    public List<Tasks> getAllTasks() {
        return taskService.getAllTasks();
    }


    @DeleteMapping(path="/delete/{taskId}")
    public String deleteTask(@PathVariable(name="taskId") Long taskId) throws TaskNotFoundException {
        return taskService.deleteTask(taskId);
    }

}
