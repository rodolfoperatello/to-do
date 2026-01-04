package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "TASK")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long userId;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "STATUS", nullable = false)
    private String taskStatus;
    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    protected TaskEntity() {
    }

    public TaskEntity(Long userid, String description, String taskStatus) {
        this.userId = userid;
        this.description = description;
        this.taskStatus = taskStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(userId,
            that.userId) && Objects.equals(description,
            that.description) && Objects.equals(taskStatus,
            that.taskStatus) && Objects.equals(createdAt,
            that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, description, taskStatus, createdAt, updatedAt);
    }
}
