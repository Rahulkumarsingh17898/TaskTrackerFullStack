package rks.tiger.com.tasktracker.model;


import rks.tiger.com.tasktracker.enums.Status;

public record TaskResponse(
        Long id,
        String name,
        String description,
        java.util.Date dueDate,
        Status status,
        Long projectId,
        Long assignedToId,
        String assignedToName
) {}