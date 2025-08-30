package org.tasks.taskmanager.Entity.mappers;

import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Entity.dto.TaskListDto;

public interface TaskListMapper {
    public TaskList taskListFromDto(TaskListDto dto);
    public TaskListDto tasklistToDto(TaskList taskList);
}
