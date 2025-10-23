package rks.tiger.com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rks.tiger.com.tasktracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
