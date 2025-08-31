package org.tasks.taskmanager.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.taskmanager.Entity.dto.TaskListDto;
import org.tasks.taskmanager.Entity.mappers.TaskListMapper;
import org.tasks.taskmanager.Services.TaskListService;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists().stream().map(taskListMapper::tasklistToDto).toList();
    }
}
