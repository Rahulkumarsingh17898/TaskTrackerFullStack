package rks.tiger.com.tasktracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProjectRequest {
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private  Long ownerId;
}
