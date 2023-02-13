package com.demo.multisport.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class LoggedUserDto {

    @NonNull
    @Size(min = 10, max = 40)
    @JsonProperty(required = true)
    private final String email;

    @NonNull
    @Size(min = 10, max = 16)
    @JsonProperty(required = true)
    private final String password;

    public LoggedUserDto() {
        this.password = "";
        this.email = "";
    }
}
