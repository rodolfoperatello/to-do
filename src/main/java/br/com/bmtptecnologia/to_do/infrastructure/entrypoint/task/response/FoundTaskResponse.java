package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FoundTaskResponse {
   private Long id;
   private String description;
   private String taskStatus;
   private LocalDateTime createdAt;

   public FoundTaskResponse() {
   }

   public FoundTaskResponse(Long id, String description, String taskStatus,
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
