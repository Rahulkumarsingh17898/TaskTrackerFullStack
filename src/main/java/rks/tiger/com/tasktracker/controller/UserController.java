package rks.tiger.com.tasktracker.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.model.CreateUserRequest;
import rks.tiger.com.tasktracker.service.UserService;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?>  createUser(@RequestBody CreateUserRequest createUserRequest){

        User user = User.builder().name(createUserRequest.getName()).email(createUserRequest.getEmail()).department(createUserRequest.getDepartment()).build();

        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }


}
