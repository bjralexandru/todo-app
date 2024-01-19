package ch.cern.todo.service;

import ch.cern.todo.dto.request.TaskDto;

import ch.cern.todo.entity.Tasks;
import ch.cern.todo.exceptions.TaskCategoryNotFoundException;
import ch.cern.todo.exceptions.TaskNotFoundException;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<Tasks> getAllTasks() {
        return tasksRepository.getAllTasks();
    }

    public String addTask (TaskDto taskDto) throws TaskCategoryNotFoundException {
        if (taskCategoryRepository.existsByCategoryName(taskDto.getCategoryName())) {
            Tasks newTask = new Tasks();
            newTask.setTaskName(taskDto.getTaskName());
            newTask.setTaskDescription(taskDto.getTaskDescription());
            newTask.setDeadline(taskDto.getDeadline());
            newTask.setCategory(taskCategoryRepository.getTaskCategoryByName(taskDto.getCategoryName()));

            tasksRepository.save(newTask);
            return "Task added successfully!";
        } else {
            String errorMessage = "Category with name '" + taskDto.getCategoryName() + "' not found.";
            throw new TaskCategoryNotFoundException(errorMessage);
        }
    }

    public String updateTask(Optional<Tasks> currentTask, TaskDto taskDto) throws TaskNotFoundException {
        if(currentTask.isPresent()){
            Tasks taskToBeUpdated = currentTask.get();
            if (taskDto.getTaskName() != null) {
                taskToBeUpdated.setTaskName(taskDto.getTaskName());
            }
            if (taskDto.getTaskDescription() != null) {
                taskToBeUpdated.setTaskDescription(taskDto.getTaskDescription());
            }
            if (taskDto.getDeadline() != null) {
                taskToBeUpdated.setDeadline(taskDto.getDeadline());
            }
            if (taskDto.getCategoryName() != null) {
                taskToBeUpdated.setCategory(taskCategoryRepository.getTaskCategoryByName(taskDto.getCategoryName()));
            }
            tasksRepository.save(taskToBeUpdated);
            return "Task updated successfully!";
        } else {
            throw new TaskNotFoundException("Task requested doesn't exist!");
        }
    }

    public String deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {

        if(tasksRepository.findById(taskId).isPresent()) {
            tasksRepository.deleteTaskByTaskId(taskId);
            return "Task with taskId: " + taskId +" was marked as complete and deleted.";
        }
        else
            throw new TaskNotFoundException("Task not found for id: " + taskId);
    }

    public Optional<Tasks> findByTaskId(Long taskId) {
        return tasksRepository.findByTaskId(taskId);
    }

}
