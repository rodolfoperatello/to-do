package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskFinder {

    @PersistenceContext
    private EntityManager entityManager;

    List<TaskEntity> findAll() {
        final String sql = "SELECT * FROM TASK";

        return this.entityManager.createNativeQuery(sql, TaskEntity.class).getResultList();
    }
}
