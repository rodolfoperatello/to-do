package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTaskRequest {
   @NotNull(message = "User ID cannot be null")
   @Positive(message = "User ID must be a positive number")
   private Long userId;

   @NotBlank(message = "Description cannot be null or empty")
   private String description;

   public CreateTaskRequest() {
   }

   public CreateTaskRequest(Long userId, String description) {
      this.userId = userId;
      this.description = description;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
