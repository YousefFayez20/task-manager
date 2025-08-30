package org.tasks.taskmanager.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="task_lists")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_list_id", updatable = false, unique = true)
    private UUID id;

    @Column(name = "title",updatable = true, nullable = false)
    private String title;

    @Column(name = "description",updatable = true)
    private String description;

    @Column(name = "created_date",updatable = false,nullable = false)
    private LocalDateTime created_date;

    @Column(name = "updated_date", updatable = true,nullable = false)
    private LocalDateTime updated_date;

    //tasklist has 0 or more task
    @OneToMany(mappedBy = "taskList",cascade = CascadeType.ALL)
    List<Task> tasks;

    public TaskList() {
    }

    public TaskList(UUID id, String title, String description, LocalDateTime created_date, LocalDateTime updated_date, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
