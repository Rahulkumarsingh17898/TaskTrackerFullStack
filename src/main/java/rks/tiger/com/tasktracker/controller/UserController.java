package rks.tiger.com.tasktracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.model.CreateUserRequest;
import rks.tiger.com.tasktracker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        System.out.println("data received for create user"+ createUserRequest.toString());
        try {
            User savedUser = userService.createUserWithDefaults(
                    createUserRequest.getName(),
                    createUserRequest.getEmail(),
                    createUserRequest.getDepartment()
            );
            return new ResponseEntity<>(savedUser, HttpStatus.OK);

        } catch (Exception e) {
            // This will catch any database errors (like duplicate emails)
            // and return a proper 400 Bad Request instead of crashing to a 401!
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }
    }
    // View all users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers(); // Ensure this exists in your service
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Assign a role to a user
    @PutMapping("/{userId}/role")
    public ResponseEntity<?> assignRole(@PathVariable Long userId, @RequestParam String roleName) {
        // You will need to implement this in your service:
        // 1. Find user by ID
        // 2. Find Role by roleName (e.g., "ROLE_ADMIN", "ROLE_USER")
        // 3. Set user.setRole(role) and save
        User updatedUser = userService.updateUserRole(userId, roleName);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}
