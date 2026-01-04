package br.com.bmtptecnologia.to_do.templates.task;

import java.time.LocalDateTime;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;

public class SearchTasksFilterTemplate {
   private static final Long USER_ID = 1L;
   private static final String DESCRIPTION = "Clean my car";
   private static final String STATUS = "CREATED";
   private static final LocalDateTime CREATED_AT_START = LocalDateTime.now();
   private static final LocalDateTime CREATED_AT_END = LocalDateTime.now().plusDays(5);

   public static SearchTasksFilter buildWithAllFields() {
      return SearchTasksFilter.builder()
          .withUserId(USER_ID)
          .withDescription(DESCRIPTION)
          .withStatus(STATUS)
          .withCreatedAtStart(CREATED_AT_START)
          .withCreatedAtEnd(CREATED_AT_END)
          .build();
   }
}
