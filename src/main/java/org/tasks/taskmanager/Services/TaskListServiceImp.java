package org.tasks.taskmanager.Services;


import org.springframework.stereotype.Service;
import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Repositories.TaskListRepository;

import java.time.LocalDateTime;
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

    @Override
    public TaskList createTaskList(TaskList taskList) {
        //exceptions to make sure that the task list doesn't hold any id, and title
        if(taskList.getId() != null){
            throw new IllegalArgumentException("the task should't exist before creating");
        }
        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException(" the task must have title");
        }
        return taskListRepository.save(new TaskList(
                null,taskList.getTitle(), taskList.getDescription(), LocalDateTime.now(),LocalDateTime.now(),null)
        );
    }


}
