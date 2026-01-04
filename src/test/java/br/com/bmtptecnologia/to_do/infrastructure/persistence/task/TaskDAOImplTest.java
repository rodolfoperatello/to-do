package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import static br.com.bmtptecnologia.to_do.templates.task.TaskTemplate.buildTaskByDescriptionAndUserId;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;
import br.com.bmtptecnologia.to_do.config.ComponentTest;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;
import br.com.bmtptecnologia.to_do.domain.user.UserId;
import br.com.bmtptecnologia.to_do.templates.task.SearchTasksFilterTemplate;

@ComponentTest
class TaskDAOImplTest {
   @Autowired
   private TaskDAO taskDAO;
   @Autowired
   private TaskFinder taskFinder;
   private static final Long FIRST_TASK = 1L;
   private static final UserId USER_ID = UserId.create(1L);
   private static final String TASK_DESCRIPTION = "Clean my car";
   private static final LocalDate LOCAL_DATE_NOW = LocalDate.now();

   @Test
   @Sql({"classpath:/sql/database.sql"})
   public void mustSaveTask() {
      // arrange
      Task taskMock = buildTaskByDescriptionAndUserId(USER_ID, TASK_DESCRIPTION);

      // act
      assertDoesNotThrow(() -> taskDAO.save(taskMock));

      // assert
      List<TaskEntity> foundTasks = this.taskFinder.findAll();

      assertEquals(1, foundTasks.size());

      TaskEntity firstTask = foundTasks.getFirst();

      assertNotNull(firstTask.getId());
      assertEquals(taskMock.getUserId().getId(), firstTask.getUserId());
      assertEquals(taskMock.getDescription(), firstTask.getDescription());
      assertEquals(taskMock.getTaskStatus().name(), firstTask.getStatus());
      assertEquals(LOCAL_DATE_NOW, firstTask.getCreatedAt().toLocalDate());
      assertEquals(LOCAL_DATE_NOW, firstTask.getUpdatedAt().toLocalDate());
   }

   @Test
   @Sql({"classpath:/sql/database.sql",
         "classpath:/sql/insert-single-task.sql"})
   public void mustFindTasksByFilterWithAllFields() {
      // arrange
      SearchTasksFilter searchTasksFilter = SearchTasksFilterTemplate.buildWithAllFields();

      // act
      List<Task> foundTasks = this.taskDAO.findTasksByFilter(searchTasksFilter);

      // assert
      assertEquals(1, foundTasks.size());

      Task firstTask = foundTasks.getFirst();

      assertEquals(FIRST_TASK, firstTask.getUserId().getId());
      assertEquals(USER_ID.getId(), firstTask.getUserId().getId());
      assertEquals(TASK_DESCRIPTION, firstTask.getDescription());
      assertEquals(LOCAL_DATE_NOW, firstTask.getCreatedAt().toLocalDate());
   }

   @Test
   @Sql({"classpath:/sql/database.sql",
         "classpath:/sql/insert-multiple-tasks-for-same-user.sql"})
   public void mustFindTasksByFilterWithUserId() {
      // arrange
      SearchTasksFilter searchTasksFilter = SearchTasksFilter.builder()
          .withUserId(USER_ID.getId())
          .build();

      // act
      List<Task> tasksByFilter = this.taskDAO.findTasksByFilter(searchTasksFilter);

      // assert
      assertEquals(4, tasksByFilter.size());

      tasksByFilter.forEach(task -> {
         assertEquals(USER_ID.getId(), task.getUserId().getId());
      });
   }

   @Test
   @Sql({"classpath:/sql/database.sql",
         "classpath:/sql/insert-multiple-tasks-for-range-filter.sql"})
   public void mustFindTasksByFilterWithDateRange() {
      // arrange
      SearchTasksFilter searchTasksFilter = SearchTasksFilter.builder()
          .withCreatedAtStart(LocalDateTime.parse("2026-01-15T13:00:00"))
          .withCreatedAtEnd(LocalDateTime.parse("2026-01-25T13:00:00"))
          .build();

      // act
      List<Task> tasksByFilter = this.taskDAO.findTasksByFilter(searchTasksFilter);

      // assert
      assertEquals(3, tasksByFilter.size());
      tasksByFilter.forEach(task -> {
         assertEquals(4, task.getUserId().getId());
      });
   }

   @Test
   @Sql({"classpath:/sql/database.sql",
         "classpath:/sql/insert-multiple-tasks-for-multiple-users.sql"})
   public void mustFindTasksByFilterWhenFiltersAreEmpty() {
      // arrange
      SearchTasksFilter searchTasksFilter = SearchTasksFilter.builder().build();

      // act
      List<Task> tasksByFilter = this.taskDAO.findTasksByFilter(searchTasksFilter);

      // assert
      assertEquals(4, tasksByFilter.size());
   }
}
