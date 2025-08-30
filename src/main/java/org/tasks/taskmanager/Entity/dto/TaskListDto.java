package org.tasks.taskmanager.Entity.dto;

import java.util.List;
import java.util.UUID;


//it can include additional attributes which are calculated based on our attributes
public record TaskListDto(
        UUID id, String title, String description, Integer count, Double progress, List<TaskDto> tasks
) {
}
