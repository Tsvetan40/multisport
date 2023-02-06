package com.demo.multisport.dto.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CommentDto {
    @NonNull
    @JsonProperty(required = true)
    @Size(max = 255)
    private String content;

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime publishedAt;

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String firstName;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastName;

    @NonNull
    @Email
    @Size(min = 10, max = 40)
    @JsonIgnore
    private String email;

    //may be at article, may be comment on center
    @JsonProperty(required = false, access = JsonProperty.Access.WRITE_ONLY)
    private String articleTitle;

    @JsonProperty(required = false)
    private String centerAddress;

    @JsonProperty(required = false, access = JsonProperty.Access.WRITE_ONLY)
    private String typeCenter;

}
