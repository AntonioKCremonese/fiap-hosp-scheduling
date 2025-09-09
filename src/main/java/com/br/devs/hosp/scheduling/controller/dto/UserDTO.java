package com.br.devs.hosp.scheduling.controller.dto;

import com.br.devs.hosp.scheduling.entities.enums.UserType;

public record UserDTO(String name, String mail, UserType userType, String expertise) {
}
