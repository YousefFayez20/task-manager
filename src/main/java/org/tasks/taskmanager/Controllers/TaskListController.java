package org.tasks.taskmanager.Controllers;


import org.springframework.web.bind.annotation.*;
import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Entity.dto.TaskListDto;
import org.tasks.taskmanager.Entity.mappers.TaskListMapper;
import org.tasks.taskmanager.Services.TaskListService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
        System.out.println("TaskListController initialized");
    }

    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists().stream().map(taskListMapper::tasklistToDto).toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.taskListFromDto(taskListDto));

        return taskListMapper.tasklistToDto(createdTaskList);
    }
    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::tasklistToDto);
    }
    @PutMapping(path = "/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable("task_list_id") UUID id, @RequestBody TaskListDto taskListDto){
        return taskListMapper.tasklistToDto(taskListService.updateTaskList(taskListMapper.taskListFromDto(taskListDto),id));
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id){
        taskListService.deleteTask(id);
    }
}
