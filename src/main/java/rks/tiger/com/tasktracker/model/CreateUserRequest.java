package rks.tiger.com.tasktracker.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateUserRequest {
    private String name;
    private String email;
    private String department;

    @Override
    public String toString() {
        return super.toString();
    }
}
