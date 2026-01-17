package rks.tiger.com.tasktracker.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rks.tiger.com.tasktracker.entity.Task;
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

    @GetMapping()
    public ResponseEntity<?> getTasksFromProjectId(@RequestParam Long projectId) {
        List<TaskResponse> tasks = taskService.getTaskFromProjectId(projectId);
        return ResponseEntity.ok().body(tasks);
    }





}
