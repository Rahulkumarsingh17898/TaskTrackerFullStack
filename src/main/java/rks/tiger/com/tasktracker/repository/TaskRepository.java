package rks.tiger.com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rks.tiger.com.tasktracker.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject_Id(Long projectId);
//    void deleteByProject_Id(Long projectId);
    @Modifying
    @Query("DELETE FROM Task t WHERE t.project.id = :projectId")
    void deleteAllTasksByProjectId(@Param("projectId") Long projectId);
}
