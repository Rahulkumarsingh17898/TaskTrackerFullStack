package rks.tiger.com.tasktracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rks.tiger.com.tasktracker.entity.Project;
import rks.tiger.com.tasktracker.entity.Task;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.enums.Status;
import rks.tiger.com.tasktracker.model.CreateTaskRequest;
import rks.tiger.com.tasktracker.model.TaskResponse;
import rks.tiger.com.tasktracker.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TaskService {
    TaskRepository taskRepository;
    ProjectService projectService;
    UserService userService;
    public List<Task> getAllTasks() {
      return taskRepository.findAll();
    }
    public TaskResponse createTask(CreateTaskRequest task) {
        Project project = projectService.getProjectById(task.getProjectId());
        User owner = userService.getUser(task.getOwnerId()); // Creator

        // Fetch the assigned user if an ID was provided
        User assignee = null;
        if (task.getAssignedToId() != null) {
            assignee = userService.getUser(task.getAssignedToId());
        }

        Task taskToSave = Task.builder()
                .name(task.getName())
                .description(task.getDescription())
                .dueDate(task.getEndDate())
                .status(Status.NOT_STARTED)
                .createdBy(owner)
                .assignedTo(assignee) // <-- Set the assignee here!
                .project(project)
                .build();

        Task saved = taskRepository.save(taskToSave);

        return new TaskResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getDueDate(),
                saved.getStatus(),
                project.getId(),
                assignee != null ? assignee.getUserId() : null,
                assignee != null ? assignee.getUserName() : "Unassigned"
        );
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No task with id " + id));
    }





    public boolean assignTaskToUser(Long taskId, Long userId) {
        User user = userService.getUser(userId);
        Task task = getTaskById(taskId);

        task.setAssignedTo(user);

        Task updatedTask = taskRepository.save(task);
        return updatedTask.getAssignedTo().equals(user);


    }
    @Transactional
    public List<TaskResponse> getTaskFromProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_Id(projectId);
        // This is where you map to the DTO
        return tasks.stream()
                .map(task -> {
                    // Access the LAZY field to force initialization
                    // This call only works if @Transactional is present!

                    // Build and return the DTO
                    return new TaskResponse(
                            task.getId(),
                            task.getName(),
                            task.getDescription(),
                            task.getDueDate(),
                            task.getStatus(),
                            task.getProject().getId(),
                            task.getAssignedTo().getUserId(),
                            task.getAssignedTo().getUserName()
                    );
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateTaskStatus(Long taskId, Status status) {
        // 1. Fetch the existing task using the method you already wrote
        Task task = getTaskById(taskId);

        // 2. Update the status
        task.setStatus(status);

        // 3. Save it back to the database
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        // Assuming you have taskRepository injected
        taskRepository.deleteById(taskId);
    }
}
