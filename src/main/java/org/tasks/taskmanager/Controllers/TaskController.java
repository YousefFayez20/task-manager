package org.tasks.taskmanager.Controllers;

import org.springframework.web.bind.annotation.*;
import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.dto.TaskDto;
import org.tasks.taskmanager.Entity.mappers.TaskMapper;
import org.tasks.taskmanager.Services.TaskListService;
import org.tasks.taskmanager.Services.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskListService taskListService;
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskListService taskListService, TaskService taskService, TaskMapper taskMapper) {
        this.taskListService = taskListService;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
        return taskService.listTasks(taskListId).stream().map(taskMapper::tasktoDto).toList();
    }
    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId ,@RequestBody TaskDto taskDto){
        Task createdTask = taskMapper.taskfromDto(taskDto);
        return taskMapper.tasktoDto( taskService.createTask(taskListId,createdTask));
    }
    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId ){
        return  taskService.getTask(taskListId,taskId).map(taskMapper::tasktoDto);
    }
    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id")UUID taskListId,@PathVariable("task_id") UUID taskId,
                              @RequestBody TaskDto taskDto){
        return taskMapper.tasktoDto(taskService.updateTask(taskListId,taskId,taskMapper.taskfromDto(taskDto)));
    }
    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable("task_list_id")UUID taskListId,@PathVariable("task_id") UUID taskId){
        taskService.deleteTask(taskListId,taskId);
    }
}
