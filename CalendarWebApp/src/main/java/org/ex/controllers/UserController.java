package org.ex.controllers;

import org.ex.models.LoginRequest;
import org.ex.models.SessionUser;
import org.ex.models.User;
import org.ex.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {

    TaskService service;

    @Autowired
    public void setTaskService(TaskService taskServiceImpl) {
        this.service = taskServiceImpl;
    }

    @PostMapping(path = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ResponseEntity createUser(@RequestBody User user) {
        System.out.println(user.getUser_type());
        boolean result = this.service.registerNewUser(user);

        if(result) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(409).build();
        }
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<SessionUser> login(@RequestBody LoginRequest loginRequest) {
        User user = this.service.getUserByUserName(loginRequest.getUser_name());

        if(user != null){
            if(user.getUser_password().equals(loginRequest.getPassword())) {
                SessionUser sessionUser = new SessionUser();
                sessionUser.setUser_name(user.getUser_name());
                sessionUser.setFirst_name(user.getFirst_name());
                sessionUser.setLast_name(user.getLast_name());
                sessionUser.setUser_type(user.getUser_type());
                sessionUser.setEmail(user.getEmail());
                return ResponseEntity.accepted().body(sessionUser);
            }
        }

        return ResponseEntity.status(403).build();
    }
}
