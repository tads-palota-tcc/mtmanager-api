package br.com.palota.mtmanager.core.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
