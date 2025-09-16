package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.controller.dto.input.UserCreateDTO;
import com.br.devs.hosp.scheduling.controller.dto.input.UserUpdateDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.UserOutputDTO;
import com.br.devs.hosp.scheduling.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphQLController {

    private final UserService userService;

    public UserGraphQLController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public Page<UserOutputDTO> getUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @QueryMapping
    public UserOutputDTO getUserById(@Argument String userId) {
        return userService.getUserById(userId);
    }

    @MutationMapping
    public UserOutputDTO createUser(@Argument UserCreateDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @MutationMapping
    public UserOutputDTO updateUser(@Argument String userId, @Argument UserUpdateDTO userDTO) {
        return userService.updateUser(userId, userDTO);
    }

    @MutationMapping
    public void deleteUser(@Argument String userId) {
        userService.deleteUser(userId);
    }
}