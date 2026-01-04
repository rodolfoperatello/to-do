package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import static br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskPersistenceMapper.toDomain;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;

@Repository
public class TaskDAOImpl implements TaskDAO {
    @PersistenceContext
    private final EntityManager entityManager;

    public TaskDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        TaskEntity entity = TaskPersistenceMapper.toEntity(task);

        this.entityManager.persist(entity);

        return toDomain((entity));
    }

    @Override
    public Optional<Task> findUserTaskByDescription(Long userId, String description) {
        try {
            final String sql = "SELECT * FROM TASK WHERE USER_ID = :userId AND DESCRIPTION = :description";

            TaskEntity taskEntity = (TaskEntity) this.entityManager.createNativeQuery(
                    sql, TaskEntity.class)
                .setParameter("userId", userId)
                .setParameter("description", description)
                .getSingleResult();

            return Optional.of(toDomain(taskEntity));
        } catch (final NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public List<Task> findAll() {
        final String sql = "SELECT * FROM TASK";

        List<TaskEntity> foundTasks = this.entityManager.createNativeQuery(sql,
            TaskEntity.class).getResultList();

        return toDomain(foundTasks);
    }
}
