package org.tasks.taskmanager.Services;


import org.springframework.stereotype.Service;
import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Repositories.TaskListRepository;

import java.util.List;

@Service
public class TaskListServiceImp  implements TaskListService{
    private final TaskListRepository taskListRepository;

    public TaskListServiceImp(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }
}
