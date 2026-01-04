package br.com.bmtptecnologia.to_do.domain.task;

import java.util.Objects;

public class TaskId {
    private final Long id;

    private TaskId(Long id) {
        this.id = id;
    }

    public static TaskId create(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (id < 0) {
            throw new IllegalArgumentException("id cannot be negative");
        }

        return new TaskId(id);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return Objects.equals(id, taskId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
