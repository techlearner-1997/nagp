package com.taskmanager.user.service;

import com.taskmanager.user.dto.Task;
import com.taskmanager.user.dto.User;
import com.taskmanager.user.exception.BusinessException;
import com.taskmanager.user.validation.UserValidation;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class TaskService {
    public static final String USER_NOT_AVAILABLE = "USER_NOT_AVAILABLE";
    public static final String USER_NOT_REGISTERED = "User not registered";
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final List<Task> tasks = new ArrayList<>();
    @Autowired
    private UserValidation validator;

    @PostConstruct
    public void init() {
        // Add default data
        tasks.add(new Task(1L, "Gym", "Go to gym regular for 1 month", "personal", "", "PENDING", "9998887776"));
        tasks.add(new Task(2L, "Gym", "Go to gym regular for 1 week", "personal", "", "PENDING", "9998887775"));
        tasks.add(new Task(3L, "Yoga", "Go to yoga regular for 1 week", "personal", "", "PENDING", "9998887774"));

    }

    public Task addTask(Task task) {
        log.info("Creating task for user {}", task.getUserId());
        task.setId(tasks.size() + 1L);
        tasks.add(task);
        return task;
    }
    public Task updateTask(Task task) {
        log.info("Updating task for user {}", task.getUserId());
        Optional<Task> first = tasks.stream().filter(x -> x.getId() == task.getId() && x.getUserId().equalsIgnoreCase(task.getUserId())).findFirst();
        if(first.isPresent()){
            tasks.remove(first.get());
            tasks.add(task);
        }

        return task;
    }
    public Task deleteTask(Long id, String userId) {
        log.info("Deleting task for user {} with Task {} ", userId, id);
        Optional<Task> first = tasks.stream().filter(x -> x.getId() == id && x.getUserId().equalsIgnoreCase(userId)).findFirst();
        if (first.isPresent()) {
            Task deletedTask=first.get();
            tasks.remove(deletedTask);
            return deletedTask;
        }
        throw new BusinessException("NO_SUCH_TASK", "Wrong Task Id");
    }
    public Task getTask(Long id, String userId) {
        log.info("Getting task for user {} with Task {} ", userId, id);
        Optional<Task> first = tasks.stream().filter(x -> x.getId() == id && x.getUserId().equalsIgnoreCase(userId)).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        throw new BusinessException("NO_SUCH_TASK", "Wrong Task Id");
    }

    public List<Task> getAllTasks(String userId) {
        log.info("Getting tasks for user {} ", userId);
        return tasks.stream().filter(x -> x.getUserId().equalsIgnoreCase(userId)).collect(Collectors.toList());
    }

    public List<Task> getAllTasksByCategory(String userId, String category) {
        log.info("Getting tasks for user {} ", userId);
        return tasks.stream().filter(x -> x.getUserId().equalsIgnoreCase(userId) && x.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }
}
