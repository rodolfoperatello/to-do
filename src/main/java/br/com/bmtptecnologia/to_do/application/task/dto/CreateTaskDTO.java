package br.com.bmtptecnologia.to_do.application.task.dto;

public class CreateTaskDTO {
    private final Long userId;
    private final String description;

    public CreateTaskDTO(Long userId, String description) {
        this.userId = userId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Long getUserId() {
        return userId;
    }
}
