package com.demo.multisport.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class LoggedUser {

    @NotBlank
    @Size(min = 4, max = 40)
    private final String email;
    @NotBlank
    @Size(min = 10, max = 26)
    private final String password;

    public LoggedUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
