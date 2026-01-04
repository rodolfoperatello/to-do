package br.com.bmtptecnologia.to_do.application.task.dto;

import java.time.LocalDateTime;

public class TaskDTO {
   private final Long id;
   private final String description;
   private final String taskStatus;
   private final LocalDateTime createdAt;

   public TaskDTO(Long id, String description, String taskStatus,
                  LocalDateTime createdAt) {
      this.id = id;
      this.description = description;
      this.taskStatus = taskStatus;
      this.createdAt = createdAt;
   }

   public Long getId() {
      return id;
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
}
