package rks.tiger.com.tasktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rks.tiger.com.tasktracker.entity.Project;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.model.ProjectResponse;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {



    @Query("""
     select new rks.tiger.com.tasktracker.model.ProjectResponse(
       p.id, p.name, p.description, p.startDate, p.endDate,
       p.createdBy.id, p.createdBy.name
     )
     from Project p
     where p.createdBy.id = :ownerId
  """)
    List<ProjectResponse> findAllByOwnerIdAsDto(@Param("ownerId") Long ownerId);

    @Query("""
     select new rks.tiger.com.tasktracker.model.ProjectResponse(
       p.id, p.name, p.description, p.startDate, p.endDate,
       p.createdBy.id, p.createdBy.name
     )
     from Project p
  """)
    List<ProjectResponse>  findAllProjects();

    @Modifying
    @Query("delete from Project p where p.id = :id")
    int deleteByIdReturningCount(@Param("id") Long id); // 0 or 1


    List<Project> findByCreatedById(Long ownerId);
}
