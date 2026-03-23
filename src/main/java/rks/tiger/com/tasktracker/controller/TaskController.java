package rks.tiger.com.tasktracker.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rks.tiger.com.tasktracker.entity.Task;
import rks.tiger.com.tasktracker.enums.Status;
import rks.tiger.com.tasktracker.model.CreateTaskRequest;
import rks.tiger.com.tasktracker.model.TaskResponse;
import rks.tiger.com.tasktracker.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {

    TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest task) {
        TaskResponse savedTask = taskService.createTask(task);
       return ResponseEntity.ok().body(savedTask);
    }

    @PostMapping("/assignTo")
    public ResponseEntity<?> assignTaskToUser(@RequestParam Long taskId, @RequestParam Long userId) {
        boolean assigned = taskService.assignTaskToUser(taskId,userId);
        if(assigned)
          return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/allTask")
    public ResponseEntity<?> getTask() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }
// get all tasks from a project id
    @GetMapping()
    public ResponseEntity<?> getTasksFromProjectId(@RequestParam Long projectId) {
        List<TaskResponse> tasks = taskService.getTaskFromProjectId(projectId);
        return ResponseEntity.ok().body(tasks);
    }


    // 1. Fetch all available statuses
    @GetMapping("/statuses")
    public ResponseEntity<Status[]> getAllStatuses() {
        // This automatically returns ["NOT_STARTED", "IN_PROGRESS", "COMPLETED", etc.]
        return ResponseEntity.ok(Status.values());
    }

    // 2. Update a task's status
    @PutMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, @RequestParam Status status) {
        // NOTE: You will need to add this 'updateTaskStatus' method to your TaskService!
        // It should just be: Task task = repo.findById(taskId); task.setStatus(status); repo.save(task);
        taskService.updateTaskStatus(taskId, status);
        return ResponseEntity.ok().build();
    }

    // Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().body("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting task: " + e.getMessage());
        }
    }



}
