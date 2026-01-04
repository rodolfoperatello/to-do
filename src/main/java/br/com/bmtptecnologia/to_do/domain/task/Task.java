package br.com.bmtptecnologia.to_do.domain.task;

import static br.com.bmtptecnologia.to_do.domain.task.TaskStatus.CREATED;

import java.time.LocalDateTime;
import java.util.Objects;

import br.com.bmtptecnologia.to_do.domain.user.UserId;

public class Task {
    private final TaskId taskId;
    private final UserId userId;
    private final String description;
    private final TaskStatus taskStatus;
    private final LocalDateTime createdAt;

    private Task(TaskId taskId, UserId userId, String description, TaskStatus taskStatus,
                 LocalDateTime createdAt) {
        this.taskId = taskId;
        this.userId = userId;
        this.description = description;
        this.taskStatus = taskStatus;
        this.createdAt = createdAt;
    }

    public static Task create(UserId userId, String description) {
        if (Objects.isNull(description) || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }

        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        return new Task(null, userId, description, CREATED, null);
    }

    public static Task recreate(Long taskId, Long userId, String description, TaskStatus taskStatus,
                                LocalDateTime createdAt) {
        if (Objects.isNull(taskId)) {
            throw new IllegalArgumentException("taskId cannot be null");
        }

        if (Objects.isNull(userId)) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        if (Objects.isNull(description) || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }

        if (Objects.isNull(taskStatus)) {
            throw new IllegalArgumentException("task status cannot be null");
        }

        if (Objects.isNull(createdAt)) {
            throw new IllegalArgumentException("created at cannot be null");
        }

        return new Task(TaskId.create(taskId), UserId.create(userId), description, taskStatus,
            createdAt);
    }

    public TaskId getTaskId() {
        return taskId;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(userId,
            task.userId) && Objects.equals(description,
            task.description) && taskStatus == task.taskStatus && Objects.equals(createdAt,
            task.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, description, taskStatus, createdAt);
    }
}
