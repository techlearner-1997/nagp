package com.taskmanager.user.controller;

import com.taskmanager.user.dto.Task;
import com.taskmanager.user.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    @Autowired
    private TaskService service;
    @PostMapping("/addTask")
    private ResponseEntity<Task> addTask(@RequestBody Task task){
        return ResponseEntity.ok(service.addTask(task));
    }
    @PostMapping("/updateTask")
    private ResponseEntity<Task> updateTask(@RequestBody Task task){

        return ResponseEntity.ok(service.updateTask(task));
    }
    @GetMapping("/deleteTask")
    private ResponseEntity<Task> deleteTask(@RequestParam("userId") String userId,@RequestParam("id")Long id){
        return ResponseEntity.ok(service.deleteTask(id,userId));
    }
    @GetMapping("/getTask")
    private ResponseEntity<Task> getTask(@RequestParam("userId") String userId,@RequestParam("id")Long id){
        return ResponseEntity.ok(service.getTask(id,userId));
    }

    @GetMapping("/getAllTasks")
    private ResponseEntity<List<Task>> getAllTasks(@RequestParam("userId") String userId){
        return ResponseEntity.ok(service.getAllTasks(userId));
    }

    @GetMapping("/getAllTasksByCategory")
    private ResponseEntity<List<Task>> getAllTasksByCatergory(@RequestParam("userId") String userId, @RequestParam("category") String category){
        return ResponseEntity.ok(service.getAllTasksByCategory(userId,category));
    }
}
