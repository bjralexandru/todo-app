package ch.cern.todo.controller;

import ch.cern.todo.dto.request.TaskCategoryDto;
import ch.cern.todo.dto.response.TaskCategoryUpdateInfoDto;
import ch.cern.todo.entity.TaskCategories;
import ch.cern.todo.entity.Tasks;
import ch.cern.todo.exceptions.TaskCategoryNotFoundException;
import ch.cern.todo.service.TaskCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoriesService taskCategoriesService;

    @PutMapping("/new_task_category")
    public ResponseEntity<String> createTaskCategory(@RequestBody TaskCategoryDto taskCategoryDto) {
        return ResponseEntity.ok().body(taskCategoriesService.createNewTaskCategory(taskCategoryDto));
    }

    @PutMapping("/update_category/{categoryId}")
    public ResponseEntity<Object> updateTaskCategory(@PathVariable Long categoryId,
                                                     @RequestBody TaskCategoryDto taskCategoryDto) {
        Optional<TaskCategories> taskCategoriesToUpdate = taskCategoriesService.findByCategoryId(categoryId);
        taskCategoriesService.updateTaskCategory(taskCategoriesToUpdate, taskCategoryDto);
        Optional<TaskCategories> taskCategoriesAfterUpdate = taskCategoriesService.findByCategoryId(categoryId);
        TaskCategoryUpdateInfoDto taskCategoryUpdateInfoDto = new TaskCategoryUpdateInfoDto(taskCategoriesAfterUpdate.get().getCategoryName(),
                taskCategoriesAfterUpdate.get().getCategoryDescription());
        return ResponseEntity.status(201).body(taskCategoryUpdateInfoDto);
    }

    @GetMapping("/all")
    public List<TaskCategories> getAllTasks() {
        return taskCategoriesService.getAllTasks();
    }

    @DeleteMapping(path="/delete/{categoryId}")
    public String deleteTask(@PathVariable(name="categoryId") Long categoryId) throws TaskCategoryNotFoundException {
        try {
            return taskCategoriesService.deleteTaskCategory(categoryId);
        } catch (TaskCategoryNotFoundException t) {
            t.setErrorMessage("Task with categoryId: " + categoryId + " not found!" );
            return t.getErrorMessage();
        }
    }
}
