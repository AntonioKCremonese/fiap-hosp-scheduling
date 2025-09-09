package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.controller.dto.UserDTO;
import com.br.devs.hosp.scheduling.entities.User;
import com.br.devs.hosp.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserGraphQLController {

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User getUserById(@Argument("userId") String userId) {
        return userService.getUserById(userId);
    }

    @MutationMapping
    public User createUser(@Argument UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @MutationMapping
    public User updateUser(@Argument String userId, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @MutationMapping
    public void deleteUser(@Argument("userId") String userId) {
        userService.deleteUser(userId);
    }
}
