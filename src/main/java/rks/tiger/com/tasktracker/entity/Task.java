package rks.tiger.com.tasktracker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import rks.tiger.com.tasktracker.enums.Status;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends Base{
    public String name;
    public String description;
    public Date dueDate;
    @Enumerated
    public Status status;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "created_by_id",nullable = false,foreignKey = @ForeignKey(name = "fk_task_created_by"))
    public User createdBy;
//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    @JoinColumn(name = "assigned_to_user_id",nullable = true,foreignKey = @ForeignKey(name = "fk_task_assigned_to"))
//    public User assignedTo;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)   // <-- allow null
    @JoinColumn(name = "assigned_to_user_id", nullable = true,
            foreignKey = @ForeignKey(name = "fk_task_assigned_to"))
    private User assignedTo;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project_id"))
    public Project project;


}
