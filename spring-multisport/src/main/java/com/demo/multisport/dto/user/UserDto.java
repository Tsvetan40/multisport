package com.demo.multisport.dto.user;

import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Builder
public class UserDto {

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(required = true)
    private String firstName;

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(required = true)
    private String secondName;

    @NonNull
    @Email
    @Size(min = 10, max = 40)
    @JsonProperty(required = true)
    private String email;

    @NonNull
    @Size(min = 10, max = 26)
    @JsonProperty(required = true)
    private String password;

    @NonNull
    @Positive
    @JsonProperty(required = true)
    private Integer age;

    @JsonProperty(required = false)
    private List<CommentDto> comments;

    @JsonProperty(required = false)
    private Plan plan;
}
