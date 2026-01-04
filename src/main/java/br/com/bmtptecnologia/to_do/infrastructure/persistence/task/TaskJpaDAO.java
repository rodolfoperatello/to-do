package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskJpaDAO extends JpaRepository<TaskEntity, Long>,
    JpaSpecificationExecutor<TaskEntity> {
}
