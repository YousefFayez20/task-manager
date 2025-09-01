package org.tasks.taskmanager.Services;


import org.springframework.stereotype.Service;
import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Repositories.TaskListRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Optional<TaskList> getTaskList(UUID uuid) {
        return taskListRepository.findById(uuid);
    }

    @Override
    public TaskList updateTaskList(TaskList taskList, UUID uuid) {
        //check id isn't null
        if (taskList.getId() == null){
            throw new IllegalArgumentException("Task don't exist");

        }
        if(taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have title");
        }
        //find task by id
        TaskList existingtaskList = taskListRepository.findById(uuid).orElseThrow(() ->  new IllegalStateException("task list not found"));
        //change task with new attributes
        existingtaskList.setDescription(taskList.getDescription());
        existingtaskList.setUpdated_date(LocalDateTime.now());
        existingtaskList.setTitle(taskList.getTitle());

        return taskListRepository.save(existingtaskList);
    }

    @Override
    public void deleteTask(UUID uuid) {
        taskListRepository.deleteById(uuid);
    }


}
