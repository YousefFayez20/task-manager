package org.tasks.taskmanager.Exceptions;

public record ErrorMessage(
        String title,
        int error,
        String description
) {
}
