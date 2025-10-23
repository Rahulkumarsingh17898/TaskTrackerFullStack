package rks.tiger.com.tasktracker.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rks.tiger.com.tasktracker.entity.Project;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.model.CreateProjectRequest;
import rks.tiger.com.tasktracker.model.ProjectResponse;
import rks.tiger.com.tasktracker.model.UpdateProjectRequest;
import rks.tiger.com.tasktracker.repository.ProjectRepository;
import rks.tiger.com.tasktracker.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProjectService {

    ProjectRepository projectRepository;
    UserService userService;
    UserRepository userRepository;

    public Project createProject(CreateProjectRequest createProjectRequest) {

        User user = userService.getUser(createProjectRequest.getOwnerId());
        if(user.getName().contains("default")){
            userService.save(user);
        }
        Project project = Project.builder()
                .name(createProjectRequest.getProjectName())
                .description(createProjectRequest.getDescription())
                .startDate(createProjectRequest.getStartDate())
                .endDate(createProjectRequest.getEndDate())
                .createdBy(user).build();

       return projectRepository.save(project);
    }

    @Transactional
    public ProjectResponse updateProject(UpdateProjectRequest req) {
        Project p = projectRepository.findById(req.getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + req.getId()));

        // Apply changes only if provided (null => no change)
        if (req.getName() != null)        p.setName(req.getName());
        if (req.getDescription() != null) p.setDescription(req.getDescription());
        if (req.getStartDate() != null)   p.setStartDate(req.getStartDate());
        if (req.getEndDate() != null)     p.setEndDate(req.getEndDate());

        Project saved = projectRepository.save(p);

        // Build response (safe inside tx; owner is accessible)
        return new ProjectResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getStartDate(),
                saved.getEndDate(),
                saved.getCreatedBy() != null ? saved.getCreatedBy().getId() : null,
                saved.getCreatedBy() != null ? saved.getCreatedBy().getName() : null
        );
    }

    public List<ProjectResponse> getProjects() {
        return projectRepository.findAllProjects();
    }
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByOwnerId(Long ownerId) {
//        List<Project> byCreatedBy = projectRepository.findByCreatedById(ownerId);
//        byCreatedBy.forEach(System.out::println);
        return projectRepository.findAllByOwnerIdAsDto(ownerId);
    }
    @Transactional
    public void deleteProjectById(Long projectId) {
        int affected = projectRepository.deleteByIdReturningCount(projectId);
        if(affected == 0){
            throw new NoSuchElementException();
        }

    }

    public Project getProjectById(Long projectId) {
      return  projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("Project Id Does Not exist for ProjectId"+projectId));
    }
}
