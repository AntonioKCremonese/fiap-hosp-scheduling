package com.br.devs.hosp.scheduling.controller.dto.output;

import com.br.devs.hosp.scheduling.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputDTO {
    private String id;
    private String name;
    private String email;
    private String login;
    private UserType userType;
}