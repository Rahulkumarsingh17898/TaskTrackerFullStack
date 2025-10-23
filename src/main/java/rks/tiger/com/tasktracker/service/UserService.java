package rks.tiger.com.tasktracker.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import rks.tiger.com.tasktracker.entity.Role;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.repository.UserRepository;

import java.util.List;

import static rks.tiger.com.tasktracker.enums.RoleType.reader;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;

    public User getUser(long id) {
        return userRepository.findById(id)
                .orElse(new User("default","default.com","defaultDept"));
//        todo: Save The User ith Roles
//                        , List.of(new Role(reader))));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
