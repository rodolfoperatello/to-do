package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import static br.com.bmtptecnologia.to_do.domain.task.TaskStatus.CREATED;
import static br.com.bmtptecnologia.to_do.templates.task.TaskTemplate.buildTaskByDescriptionAndUserId;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.bmtptecnologia.to_do.config.ComponentTest;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;
import br.com.bmtptecnologia.to_do.domain.user.UserId;

@ComponentTest
class TaskDAOImplTest {
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private TaskFinder taskFinder;
    private static final Long FIRST_TASK = 1L;
    private static final UserId USER_ID = UserId.create(1L);
    private static final String FIRST_TASK_DESCRIPTION = "Clean my car";
    private static final LocalDate LOCAL_DATE_NOW = LocalDate.now();

    @Test
    public void mustSaveTask() {
        // arrange
        Task taskMock = buildTaskByDescriptionAndUserId(USER_ID, FIRST_TASK_DESCRIPTION);

        // act
        assertDoesNotThrow(() -> taskDAO.save(taskMock));

        // assert
        List<TaskEntity> foundTasks = this.taskFinder.findAll();

        assertEquals(1, foundTasks.size());

        TaskEntity firstTask = foundTasks.getFirst();

        assertNotNull(firstTask.getId());
        assertEquals(taskMock.getUserId().getId(), firstTask.getUserId());
        assertEquals(taskMock.getDescription(), firstTask.getDescription());
        assertEquals(taskMock.getTaskStatus().name(), firstTask.getTaskStatus());
        assertEquals(LOCAL_DATE_NOW, firstTask.getCreatedAt().toLocalDate());
        assertEquals(LOCAL_DATE_NOW, firstTask.getUpdatedAt().toLocalDate());
    }

    @Test
    @Sql({"classpath:sql/database.sql",
          "classpath:sql/insert-multiple-tasks-for-multiple-users.sql"})
    public void mustFindUserTasksByDescription() {
        // act
        Optional<Task> foundTaskOptional = this.taskDAO.findUserTaskByDescription(USER_ID.getId(),
            FIRST_TASK_DESCRIPTION);

        // assert
        assertNotNull(foundTaskOptional);
        assertTrue(foundTaskOptional.isPresent());

        Task task = foundTaskOptional.get();

        assertEquals(FIRST_TASK, task.getTaskId().getId());
        assertEquals(FIRST_TASK_DESCRIPTION, task.getDescription());
    }

    @Test
    @Sql({"classpath:sql/database.sql",
          "classpath:sql/insert-multiple-tasks-for-multiple-users.sql"})
    public void mustFindAllTasks() {
        // act
        List<Task> foundTasks = this.taskDAO.findAll();

        // assert
        assertFalse(foundTasks.isEmpty());
        assertEquals(2, foundTasks.size());

        for (int i = 0; i < foundTasks.size(); i++) {
            Task foundTask = foundTasks.get(i);

            assertEquals(i + 1, foundTask.getTaskId().getId());
            assertEquals(i + 1, foundTask.getUserId().getId());
            assertEquals(CREATED, foundTask.getTaskStatus());
            assertEquals(LOCAL_DATE_NOW, foundTask.getCreatedAt().toLocalDate());
            assertFalse(foundTask.getDescription().isEmpty());
        }
    }
}
