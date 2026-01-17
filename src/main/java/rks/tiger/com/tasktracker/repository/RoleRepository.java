package rks.tiger.com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rks.tiger.com.tasktracker.entity.Role;
import rks.tiger.com.tasktracker.enums.AppRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}