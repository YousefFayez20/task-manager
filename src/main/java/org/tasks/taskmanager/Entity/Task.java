package org.tasks.taskmanager.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false,name = "task_id",unique = true,nullable = false)
    private UUID id;


    @Column(name = "title",nullable = false,updatable = true)
    private String title;
    @Column(name = "description",nullable = true,updatable = true)
    private String description;
    @Column(name = "dueDate",updatable = true)
    private LocalDateTime dueDate;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime created_date;

    @Column(name = "updated_date", updatable = true)
    private LocalDateTime updated_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
    //@JoinColumn(name = "task_list_id") specifies the foreign key column name in tasks table

    @Column(name = "status",nullable = false )
    private TaskStatus status;

    @Column(name = "priority",  nullable = false)
    private TaskPriority priority;

    public Task() {
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Task(UUID id, String title, String description, LocalDateTime dueDate, LocalDateTime created_date, LocalDateTime updated_date, TaskList taskList, TaskStatus status, TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.taskList = taskList;
        this.status = status;
        this.priority = priority;
    }
}
