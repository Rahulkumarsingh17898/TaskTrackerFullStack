package rks.tiger.com.tasktracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project extends Base{

    public String name;
    public String description;
    public Date startDate;
    public Date endDate;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "created_by_id",nullable = false,foreignKey = @ForeignKey(name = "fk_project_created_by"))
    public User createdBy;


}
