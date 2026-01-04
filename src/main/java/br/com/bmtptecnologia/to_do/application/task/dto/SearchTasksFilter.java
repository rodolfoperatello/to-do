package br.com.bmtptecnologia.to_do.application.task.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SearchTasksFilter {
   private Long userId;
   private String status;
   private String description;
   private LocalDateTime createdAtStart;
   private LocalDateTime createdAtEnd;

   private SearchTasksFilter(Long userId, String status, String description,
                            LocalDateTime createdAtStart, LocalDateTime createdAtEnd) {
      this.userId = userId;
      this.status = status;
      this.description = description;
      this.createdAtStart = createdAtStart;
      this.createdAtEnd = createdAtEnd;
   }

   public Long getUserId() {
      return userId;
   }

   public String getStatus() {
      return status;
   }

   public String getDescription() {
      return description;
   }

   public LocalDateTime getCreatedAtStart() {
      return createdAtStart;
   }

   public LocalDateTime getCreatedAtEnd() {
      return createdAtEnd;
   }

   public static SearchTaskFilterBuilder builder() {
      return new SearchTaskFilterBuilder();
   }

   public static class SearchTaskFilterBuilder {
      private Long userId;
      private String status;
      private String description;
      private LocalDateTime createdAtStart;
      private LocalDateTime createdAtEnd;

      public SearchTaskFilterBuilder withUserId(Long userId) {
         if (Objects.nonNull(userId)) {
            this.userId = userId;
         }

         return this;
      }

      public SearchTaskFilterBuilder withStatus(String status) {
         if (Objects.nonNull(status)) {
            this.status = status;
         }

         return this;
      }

      public SearchTaskFilterBuilder withDescription(String description) {
         if (Objects.nonNull(description)) {
            this.description = description;
         }

         return this;
      }

      public SearchTaskFilterBuilder withCreatedAtStart(LocalDateTime createdAtStart) {
         if (Objects.nonNull(createdAtStart)) {
            this.createdAtStart = createdAtStart;
         }

         return this;
      }

      public SearchTaskFilterBuilder withCreatedAtEnd(LocalDateTime createdAtEnd) {
         if (Objects.nonNull(createdAtEnd)) {
            this.createdAtEnd = createdAtEnd;
         }

         return this;
      }

      public SearchTasksFilter build() {
         return new SearchTasksFilter(this.userId, this.status, this.description,
             this.createdAtStart, this.createdAtEnd);
      }
   }
}
