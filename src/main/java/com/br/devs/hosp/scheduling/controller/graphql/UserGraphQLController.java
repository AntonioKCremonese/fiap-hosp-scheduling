package com.br.devs.hosp.scheduling.controller.graphql;

import com.br.devs.hosp.scheduling.controller.dto.output.UserOutputDTO;
import com.br.devs.hosp.scheduling.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

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
}