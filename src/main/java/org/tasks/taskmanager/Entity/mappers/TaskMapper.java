package org.tasks.taskmanager.Entity.mappers;

import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.dto.TaskDto;
import org.tasks.taskmanager.Entity.dto.TaskListDto;


public interface TaskMapper {
    public Task taskfromDto(TaskDto dto);
    public TaskDto tasktoDto(Task task);

}
