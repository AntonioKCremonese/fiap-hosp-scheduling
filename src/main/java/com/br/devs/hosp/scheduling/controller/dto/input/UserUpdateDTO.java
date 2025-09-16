package com.br.devs.hosp.scheduling.controller.dto.input;

import com.br.devs.hosp.scheduling.entities.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

    @Size(max = 125, message = "O nome deve ter no máximo 125 caracteres")
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Size(max = 200, message = "O e-mail deve ter no máximo 200 caracteres")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Size(max = 200, message = "O login deve ter no máximo 200 caracteres")
    @NotBlank(message = "O login é obrigatório")
    private String login;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private UserType userType;
}