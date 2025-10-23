package rks.tiger.com.tasktracker.model;


public record TaskResponse(
        Long id,
        String name,
        String description,
        java.util.Date dueDate,
        String status,
        Long createdById, String createdByName,
        Long projectId, String projectName
) {}