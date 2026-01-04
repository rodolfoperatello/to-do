package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreatedTaskResponse {
   private final Long taskId;
   private final String description;
   private final String taskStatus;
   private final LocalDateTime createdAt;
   private final LocalDateTime updatedAt;

   public CreatedTaskResponse(Long taskId, String description, String taskStatus,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
      this.taskId = taskId;
      this.description = description;
      this.taskStatus = taskStatus;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
   }

   public Long getTaskId() {
      return taskId;
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
}
