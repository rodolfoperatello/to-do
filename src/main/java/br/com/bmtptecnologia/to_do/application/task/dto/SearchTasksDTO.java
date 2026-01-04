package br.com.bmtptecnologia.to_do.application.task.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SearchTasksDTO {
   private Long userId;
   private String description;
   private String status;
   private LocalDateTime createdAtStart;
   private LocalDateTime createdAtEnd;

   private SearchTasksDTO(Long userId, String description, String status,
                         LocalDateTime createdAtStart, LocalDateTime createdAtEnd) {
      this.userId = userId;
      this.description = description;
      this.status = status;
      this.createdAtStart = createdAtStart;
      this.createdAtEnd = createdAtEnd;
   }

   public static SearchTaskDTOBuilder builder(){
      return new SearchTaskDTOBuilder();
   }

   public Long getUserId() {
      return userId;
   }

   public String getDescription() {
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

   public static class SearchTaskDTOBuilder {
      private Long userId;
      private String description;
      private String status;
      private LocalDateTime createdAtStart;
      private LocalDateTime createdAtEnd;

      public SearchTaskDTOBuilder withUserId(Long userId) {
         if (Objects.nonNull(userId)) {
            this.userId = userId;
         }

         return this;
      }

      public SearchTaskDTOBuilder withDescription(String description) {
         if (Objects.nonNull(description)) {
            this.description = description;
         }

         return this;
      }

      public SearchTaskDTOBuilder withStatus(String status) {
         if (Objects.nonNull(status)) {
            this.status = status;
         }

         return this;
      }

      public SearchTaskDTOBuilder withCreatedAtStart(LocalDateTime createdAtStart) {
         if (Objects.nonNull(createdAtStart)) {
            this.createdAtStart = createdAtStart;
         }

         return this;
      }

      public SearchTaskDTOBuilder withCreatedAtEnd(LocalDateTime createdAtEnd) {
         if (Objects.nonNull(createdAtEnd)) {
            this.createdAtEnd = createdAtEnd;
         }

         return this;
      }

      public SearchTasksDTO build(){
         return new SearchTasksDTO(this.userId, this.description, this.status, this.createdAtStart,
             this.createdAtEnd);
      }
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
