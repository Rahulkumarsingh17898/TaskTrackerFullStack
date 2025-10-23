package rks.tiger.com.tasktracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rks.tiger.com.tasktracker.entity.Project;
import rks.tiger.com.tasktracker.entity.Task;
import rks.tiger.com.tasktracker.entity.User;
import rks.tiger.com.tasktracker.enums.Status;
import rks.tiger.com.tasktracker.model.CreateTaskRequest;
import rks.tiger.com.tasktracker.model.TaskResponse;
import rks.tiger.com.tasktracker.repository.TaskRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
        User owner = userService.getUser(task.getOwnerId());
        Task taskToSave = Task.builder().name(task.getName()).description(task.getDescription()).dueDate(task.getEndDate())
                .status(Status.NOT_STARTED).createdBy(owner).project(project).build();
        Task saved = taskRepository.save(taskToSave);

        return new TaskResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getDueDate(),
                saved.getStatus().name(),
                owner.getId(), owner.getName(),
                project.getId(), project.getName()
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
}
