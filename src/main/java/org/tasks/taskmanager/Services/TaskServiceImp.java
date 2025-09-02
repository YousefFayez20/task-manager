package org.tasks.taskmanager.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.TaskPriority;
import org.tasks.taskmanager.Entity.TaskStatus;
import org.tasks.taskmanager.Repositories.TaskListRepository;
import org.tasks.taskmanager.Repositories.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;
    private final TaskListService taskListService;


    public TaskServiceImp(TaskRepository taskRepository, TaskListRepository taskListRepository, TaskListService taskListService) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
        this.taskListService = taskListService;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId() != null){
            throw new IllegalArgumentException("task already has an ID");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        if(task.getTitle().isBlank() || task.getTitle() == null){
            throw new IllegalArgumentException("task should have a title");
        }

        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(), task.getDueDate() ,LocalDateTime.now(),LocalDateTime.now(),taskListService.getTaskList(taskListId).orElseThrow(() ->new IllegalArgumentException("invalid task id provided")),
                TaskStatus.OPEN,taskPriority
        ));
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return Optional.ofNullable(taskRepository.findByTaskListIdAndId(taskListId,taskId));
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null){
            throw new IllegalArgumentException("task id not available");
        }
        if (task.getTitle().isBlank()){
            throw new IllegalArgumentException("title shouldn't be empty");
        }
        Task updatedTask = taskRepository.findByTaskListIdAndId(taskListId,taskId);
        updatedTask.setDescription(task.getDescription());
        updatedTask.setUpdated_date(LocalDateTime.now());
        updatedTask.setTitle(task.getTitle());
        updatedTask.setPriority(task.getPriority());
        updatedTask.setStatus(task.getStatus());

        return taskRepository.save(updatedTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
