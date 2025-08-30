package org.tasks.taskmanager.Entity.dto;

import org.tasks.taskmanager.Entity.TaskPriority;
import org.tasks.taskmanager.Entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto (
        UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority
){
}
