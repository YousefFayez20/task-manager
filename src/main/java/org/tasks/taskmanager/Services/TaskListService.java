package org.tasks.taskmanager.Services;

import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);

}
