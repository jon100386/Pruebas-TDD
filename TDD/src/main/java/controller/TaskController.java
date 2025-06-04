package controller;

import model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TaskService;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String description = payload.get("description");
        Task created = taskService.createTask(title, description);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}