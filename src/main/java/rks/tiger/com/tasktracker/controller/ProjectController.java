package rks.tiger.com.tasktracker.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rks.tiger.com.tasktracker.entity.Project;
import rks.tiger.com.tasktracker.model.CreateProjectRequest;
import rks.tiger.com.tasktracker.model.ProjectResponse;
import rks.tiger.com.tasktracker.model.UpdateProjectRequest;
import rks.tiger.com.tasktracker.service.ProjectService;

import java.util.List;

@RestController()
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {

    ProjectService projectService;


    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
        Project projectCreated = projectService.createProject(createProjectRequest);
        return new ResponseEntity<>(projectCreated, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProject( @RequestBody UpdateProjectRequest updateProjectRequest){
        ProjectResponse projectResponse = projectService.updateProject(updateProjectRequest);
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectResponse> projects = projectService.getProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/userId")
    public ResponseEntity<?> getProjectsByUserId(@RequestParam Long userId) {
        System.out.println("Got Request for getting projects by userId " + userId);
        List<?> projects = projectService.getProjectsByOwnerId(userId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProject(@RequestParam Long projectId) {
        System.out.println("Got Request for deleting projects by projectId " + projectId);
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
