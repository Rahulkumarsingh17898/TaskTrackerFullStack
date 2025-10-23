package rks.tiger.com.tasktracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User extends Base{
    public String name;
    @Column(unique = true)
    public String email;
    public String department;
//    @OneToMany
//    public List<Role> roles= new ArrayList<>();
}
