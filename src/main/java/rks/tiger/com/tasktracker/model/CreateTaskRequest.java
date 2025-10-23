package rks.tiger.com.tasktracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateTaskRequest {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private  Long ownerId;
    private Long projectId;
}
