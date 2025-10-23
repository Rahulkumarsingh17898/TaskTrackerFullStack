package rks.tiger.com.tasktracker.model;

import java.util.Date;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        Date startDate,
        Date endDate,
        Long createdById,
        String createdByName
) {}