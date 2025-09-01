package org.tasks.taskmanager.Services;

import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID uuid);
    TaskList updateTaskList(TaskList taskList,UUID uuid);
    void deleteTask(UUID uuid);


}
