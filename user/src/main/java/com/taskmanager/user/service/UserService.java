package com.taskmanager.user.service;

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

@Service
public class UserService {
    public static final String USER_NOT_AVAILABLE = "USER_NOT_AVAILABLE";
    public static final String USER_NOT_REGISTERED = "User not registered";
    private final Logger log= LoggerFactory.getLogger(UserService.class);
    private final List<User> users=new ArrayList<>();
    @Autowired
    private UserValidation validator;

    @PostConstruct
    public void init() {
        // Add default data
        users.add(new User("Deep",new BCryptPasswordEncoder().encode("12345"),"deep123@gmail.com","9998887776"));
        users.add(new User("Gaurav",new BCryptPasswordEncoder().encode("23456"),"gaurav123@gmail.com","9998887775"));
        users.add(new User("Manvi",new BCryptPasswordEncoder().encode("34567"),"maanu123@gmail.com","9998887774"));

    }

    public void createUser(User user){
        log.info("Creating User");
        validator.isValidPhone(user.getPhone());
        Optional<User> existingUser = users.stream().filter(x -> x.getPhone().equalsIgnoreCase(user.getPhone())).findAny();
        if(existingUser.isPresent()){
            throw new BusinessException("EXISTING_USER","User already available");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        users.add(user);
    }

    public User getUser(String email){
        log.info("Get UserDetails by email {} ",email);
        Optional<User> user = users.stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findFirst();
        if(user.isEmpty()){
            throw new BusinessException(USER_NOT_AVAILABLE,USER_NOT_REGISTERED);
        }
        return user.get();
    }

    public User updateUser(User user) {
        log.info("Updating user {} :",user.getEmail());
        Optional<User> optUser = users.stream().filter(x -> x.getEmail().equalsIgnoreCase(user.getEmail())).findAny();
        if(optUser.isEmpty()){
            throw new BusinessException(USER_NOT_AVAILABLE,USER_NOT_REGISTERED);
        }
        User existingUser=optUser.get();
        existingUser=user;
        return existingUser;
    }
}
