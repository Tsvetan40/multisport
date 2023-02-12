package com.demo.multisport.dto.user;

import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.user.Role;
import com.demo.multisport.entities.user.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.*;
import java.util.List;


@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Builder
public class UserDto {

    @NonNull
    @NotNull
    @Size(min = 4, max = 16)
    @JsonProperty(required = true)
    private String firstName;

    @NonNull
    @NotNull
    @Size(min = 4, max = 16)
    @JsonProperty(required = true)
    private String secondName;

    @NonNull
    @NotNull
    @Email
    @Size(min = 10, max = 40)
    @JsonProperty(required = true)
    private String email;

    @NonNull
    @NotNull
    @Size(min = 10, max = 16)
    @JsonProperty(required = true)
    private String password;

    @NonNull
    @NotNull
    @Min(value = 16)
    @Max(value = 100)
    @JsonProperty(required = true)
    private Integer age;

    @NonNull
    @NotNull
    @JsonProperty(required = true)
    private Status status;

    @NonNull
    @NotNull
    @JsonProperty(required = true)
    private Role role;

    @JsonProperty(required = false)
    private List<CommentDto> comments;

    @JsonProperty(required = false)
    private PlanDto plan;
}
