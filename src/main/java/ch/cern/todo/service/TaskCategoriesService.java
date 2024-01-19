package ch.cern.todo.service;

import ch.cern.todo.dto.request.TaskCategoryDto;
import ch.cern.todo.dto.request.TaskDto;
import ch.cern.todo.entity.TaskCategories;
import ch.cern.todo.entity.Tasks;
import ch.cern.todo.exceptions.TaskCategoryNotFoundException;
import ch.cern.todo.exceptions.TaskNotFoundException;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoriesService {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public TaskCategories getTaskCategoryByName(String categoryName) {
        return taskCategoryRepository.getTaskCategoryByName(categoryName);
    }

    public String createNewTaskCategory(TaskCategoryDto taskCategoryDto) {
        TaskCategories newTaskCategory = new TaskCategories();
        newTaskCategory.setCategoryName(taskCategoryDto.getCategoryName());
        newTaskCategory.setCategoryDescription(taskCategoryDto.getCategoryDescription());
        taskCategoryRepository.save(newTaskCategory);
        return "New category created successfully!";
    }

    public String updateTaskCategory(Optional<TaskCategories> currentTaskCategory, TaskCategoryDto taskCategoryDto)
            throws TaskCategoryNotFoundException {
        if(currentTaskCategory.isPresent()){
            TaskCategories taskCategoryToBeUpdated = currentTaskCategory.get();
            if (taskCategoryDto.getCategoryName() != null) {
                taskCategoryToBeUpdated.setCategoryName(taskCategoryDto.getCategoryName());
            }
            if (taskCategoryDto.getCategoryDescription() != null) {
                taskCategoryToBeUpdated.setCategoryDescription(taskCategoryDto.getCategoryDescription());
            }

            taskCategoryRepository.save(taskCategoryToBeUpdated);
            return "Task category updated successfully!";
        } else {
            throw new TaskNotFoundException("Task category requested doesn't exist!");
        }
    }

    public String deleteTaskCategory(@PathVariable Long categoryId) throws TaskCategoryNotFoundException {

        if(taskCategoryRepository.findById(categoryId).isPresent()) {
            taskCategoryRepository.deleteTaskByTaskId(categoryId);
            return "Task category with categoryId: " + categoryId +" was deleted!";
        }
        else
            throw new TaskNotFoundException("Task category not found for id " + categoryId);
    }

    public Optional<TaskCategories> findByCategoryId(Long categoryId) {
        return taskCategoryRepository.findById(categoryId);
    }

    public List<TaskCategories> getAllTasks() {
        return taskCategoryRepository.getAllTasks();
    }
}
