package com.demo.multisport.dto.page;

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
    @JsonProperty(required = true)
    private LocalDateTime publishedAt;

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(required = true)
    private String firstName;

    @NonNull
    @JsonProperty(required = true)
    private String lastName;

    @NonNull
    @Email
    @Size(min = 10, max = 40)
    @JsonProperty
    private String email;

    //may be at article, may be comment on center
    @JsonProperty(required = false)
    private String articleTitle;

    @JsonProperty(required = false)
    private String centerAddress;

    @JsonProperty(required = false, access = JsonProperty.Access.WRITE_ONLY)
    private String typeCenter;

}
