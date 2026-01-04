package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import static br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskPersistenceMapper.toDomain;
import static br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskSpecification.from;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;

@Repository
public class TaskDAOImpl implements TaskDAO {
    @PersistenceContext
    private final EntityManager entityManager;
    private final TaskJpaDAO taskJpaDAO;

    public TaskDAOImpl(EntityManager entityManager, TaskJpaDAO taskJpaDAO) {
        this.entityManager = entityManager;
       this.taskJpaDAO = taskJpaDAO;
    }

    @Override
    @Transactional
    public Task save(Task task) {
        TaskEntity entity = TaskPersistenceMapper.toEntity(task);

        this.entityManager.persist(entity);

        return toDomain((entity));
    }

    @Override
    public List<Task> findTasksByFilter(SearchTasksFilter searchTasksFilter) {
        Specification<TaskEntity> taskEntitySpecification = from(searchTasksFilter);

        return toDomain(this.taskJpaDAO.findAll(taskEntitySpecification));
    }
}
