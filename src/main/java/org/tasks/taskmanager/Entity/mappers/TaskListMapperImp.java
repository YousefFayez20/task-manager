package org.tasks.taskmanager.Entity.mappers;

import org.springframework.stereotype.Component;
import org.tasks.taskmanager.Entity.Task;
import org.tasks.taskmanager.Entity.TaskList;
import org.tasks.taskmanager.Entity.TaskStatus;
import org.tasks.taskmanager.Entity.dto.TaskListDto;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImp implements TaskListMapper{
    private final TaskMapper taskMapper;

    public TaskListMapperImp(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList taskListFromDto(TaskListDto dto) {
      return new TaskList( dto.id(),dto.title(),dto.description(),null,null,
              Optional.ofNullable(dto.tasks()).map(tasks -> tasks.stream().map(taskMapper::taskfromDto).toList()).orElse(null)
              );
      //this method takes dto and transfer it to pojo to deal with it
    }

    @Override
    public TaskListDto tasklistToDto(TaskList taskList) {
        final List<Task> tasks = taskList.getTasks();
        return new TaskListDto(
                taskList.getId(),taskList.getTitle(),taskList.getDescription(),Optional.ofNullable(tasks).map(List::size).orElse(0),calculateTaskListProgress(tasks),
                Optional.ofNullable(tasks).map(t -> t.stream().map(taskMapper::tasktoDto).toList()).orElse(null)
        );
    }
    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) {
            return null;
        }
        long closedTaskCount = tasks.stream()
                .filter(task-> TaskStatus.CLOSED == task.getStatus())
                .count();
        return (double) closedTaskCount / tasks.size();
    }
}
