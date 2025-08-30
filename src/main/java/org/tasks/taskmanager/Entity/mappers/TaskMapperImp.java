package org.tasks.taskmanager.Entity.mappers;

import org.springframework.stereotype.Component;
import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.dto.TaskDto;

@Component
public class TaskMapperImp implements TaskMapper{
    @Override
    public Task taskfromDto(TaskDto dto) {
        return new Task(
                dto.id(),dto.title(),dto.description(),dto.dueDate(),null,null,null,dto.status(),dto.priority()
        );
    }

    @Override
    public TaskDto tasktoDto(Task task) {
        return new TaskDto(
                task.getId(),task.getTitle(),task.getDescription(),task.getDueDate(),task.getStatus(),task.getPriority()
        );
    }
}
