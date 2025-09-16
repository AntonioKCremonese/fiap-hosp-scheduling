package com.br.devs.hosp.scheduling.mapper;

import com.br.devs.hosp.scheduling.controller.dto.input.UserCreateDTO;
import com.br.devs.hosp.scheduling.controller.dto.input.UserUpdateDTO;
import com.br.devs.hosp.scheduling.controller.dto.output.UserOutputDTO;
import com.br.devs.hosp.scheduling.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserOutputDTO toDto(User user) {
        return modelMapper.map(user, UserOutputDTO.class);
    }

    public User toEntity(UserCreateDTO userCreateDTO) {
        return modelMapper.map(userCreateDTO, User.class);
    }

    public void copyProperties(UserUpdateDTO userUpdateDTO, User user) {
        modelMapper.map(userUpdateDTO, user);
    }
}