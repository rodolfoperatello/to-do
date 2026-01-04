package br.com.bmtptecnologia.to_do.templates.task;

import java.time.LocalDateTime;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;

public class SearchTasksDTOTemplate {
   private static Long USER_ID = 1L;
   private static String DESCRIPTION = "Clean my car";
   private static String CREATED_STATUS = "CREATED";
   private static LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

   public static SearchTasksDTO buildDefaultSearchTasksDTO() {
      return SearchTasksDTO.builder()
          .withUserId(USER_ID)
          .withDescription(DESCRIPTION)
          .withStatus(CREATED_STATUS)
          .withCreatedAtStart(LOCAL_DATE_TIME_NOW)
          .withCreatedAtEnd(LOCAL_DATE_TIME_NOW)
          .build();
   }
}
