package com.demo.multisport.entities.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
@NoArgsConstructor
public class LoggedUser {

    @NotBlank
    @Size(min = 4, max = 40)
    private String email;
    @NotBlank
    @Size(min = 10, max = 26)
    private String password;

    public LoggedUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoggedUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public LoggedUser withPassword(String password) {
        this.password = password;
        return this;
    }
}
