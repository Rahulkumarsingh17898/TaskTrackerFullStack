package rks.tiger.com.tasktracker.service;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.stereotype.Service;
//import rks.tiger.com.tasktracker.entity.Role;
//import rks.tiger.com.tasktracker.entity.User;
//import rks.tiger.com.tasktracker.repository.UserRepository;
//
//import java.util.List;
//
//import static rks.tiger.com.tasktracker.enums.RoleType.reader;
//
//@AllArgsConstructor
//@Service
//public class UserService {
//    UserRepository userRepository;
//
//    public User getUser(long id) {
//        return userRepository.findById(id)
//                .orElse(new User("default","default.com","defaultDept"));
////        todo: Save The User ith Roles
////                        , List.of(new Role(reader))));
//    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//}



//import com.secure.notes.dtos.UserDTO;
//import com.secure.notes.models.Role;
//import com.secure.notes.models.User;
//import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import rks.tiger.com.tasktracker.entity.Role;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.model.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    User getUser(long id);

    User updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUsername(String username);

    void updateAccountLockStatus(Long userId, boolean lock);

    List<Role> getAllRoles();

    void updateAccountExpiryStatus(Long userId, boolean expire);

    void updateAccountEnabledStatus(Long userId, boolean enabled);

    void updateCredentialsExpiryStatus(Long userId, boolean expire);

    void updatePassword(Long userId, String password);

    void generatePasswordResetToken(String email);

    void resetPassword(String token, String newPassword);

    Optional<User> findByEmail(String email);

    User registerUser(User user);

    public User createUserWithDefaults(String name, String email, String department);
//    GoogleAuthenticatorKey generate2FASecret(Long userId);

//    boolean validate2FACode(Long userId, int code);
//
//    void enable2FA(Long userId);
//
//    void disable2FA(Long userId);
}
