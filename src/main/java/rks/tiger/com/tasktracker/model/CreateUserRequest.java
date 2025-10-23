package rks.tiger.com.tasktracker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateUserRequest {
    private String Name;
    private String email;
    private String department;
}
