package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;

public class TaskSpecification {
   private static final String USER_ID_COLUMN = "userId";
   private static final String DESCRIPTION_COLUMN = "description";
   private static final String STATUS_COLUMN = "status";
   private static final String CREATED_AT_COLUMN = "createdAt";

   public static Specification<TaskEntity> from(SearchTasksFilter searchTasksFilter) {
      return Specification
          .where(hasUserId(searchTasksFilter.getUserId()))
          .and(hasDescription(searchTasksFilter.getDescription()))
          .and(hasStatus(searchTasksFilter.getStatus()))
          .and(hasCreatedAtStart(searchTasksFilter.getCreatedAtStart()))
          .and(hasCreatedAtEnd(searchTasksFilter.getCreatedAtEnd()));
   }

   private static Specification<TaskEntity> hasUserId(Long userId) {
      return (root, query, cb) ->
          Objects.isNull(userId) ? cb.conjunction() : cb.equal(root.get(USER_ID_COLUMN), userId);
   }

   private static Specification<TaskEntity> hasDescription(String description) {
      return (root, query, cb) ->
          Objects.isNull(description) ? cb.conjunction()
              : cb.equal(root.get(DESCRIPTION_COLUMN), description);
   }

   private static Specification<TaskEntity> hasStatus(String status) {
      return (root, query, cb) ->
          Objects.isNull(status) ? cb.conjunction() : cb.equal(root.get(STATUS_COLUMN), status);
   }

   private static Specification<TaskEntity> hasCreatedAtStart(LocalDateTime createdAtStart) {
      return (root, query, cb) ->
          Objects.isNull(createdAtStart) ? cb.conjunction()
              : cb.greaterThanOrEqualTo(root.get(CREATED_AT_COLUMN),
              createdAtStart.with(LocalTime.MIN));
   }

   private static Specification<TaskEntity> hasCreatedAtEnd(LocalDateTime createdAtEnd) {
      return (root, query, cb) ->
          Objects.isNull(createdAtEnd) ? cb.conjunction()
              : cb.lessThanOrEqualTo(root.get(CREATED_AT_COLUMN),
              createdAtEnd.with(LocalTime.MAX));
   }
}
