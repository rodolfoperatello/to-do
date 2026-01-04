package br.com.bmtptecnologia.to_do.application.task.dto;

import java.time.LocalDateTime;

public class SearchUserTasksDTO {
   private final Long userId;
   private final Long description;
   private final String status;
   private final LocalDateTime createdAtStart;
   private final LocalDateTime createdAtEnd;

   public SearchUserTasksDTO(Long userId, Long description, String status,
                             LocalDateTime createdAtStart, LocalDateTime createdAtEnd) {
      this.userId = userId;
      this.description = description;
      this.status = status;
      this.createdAtStart = createdAtStart;
      this.createdAtEnd = createdAtEnd;
   }

   public Long getUserId() {
      return userId;
   }

   public Long getDescription() {
      return description;
   }

   public String getStatus() {
      return status;
   }

   public LocalDateTime getCreatedAtStart() {
      return createdAtStart;
   }

   public LocalDateTime getCreatedAtEnd() {
      return createdAtEnd;
   }

   @Override
   public String toString() {
      return "SearchUserTasksDTO{" +
          "userId=" + userId +
          ", description=" + description +
          ", status='" + status + '\'' +
          ", createdAtStart=" + createdAtStart +
          ", createdAtEnd=" + createdAtEnd +
          '}';
   }
}
