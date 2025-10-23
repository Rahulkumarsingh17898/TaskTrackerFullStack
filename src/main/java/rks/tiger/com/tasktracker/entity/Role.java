package rks.tiger.com.tasktracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rks.tiger.com.tasktracker.enums.RoleType;

@Entity(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Base {
    @Enumerated
    public RoleType roleType;

}
