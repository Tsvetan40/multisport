package com.demo.multisport.dto;

import com.demo.multisport.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class PlanDto {

    @NonNull
    @JsonProperty(required = true)
    @Size(min = 4, max = 20)
    private String name;

    @NonNull
    @JsonProperty(required = true)
    @Min(10)
    private Double price;

    @JsonProperty(required = false)
    private Set<UserDto> users;

    @JsonProperty(required = true)
    @NonNull
    @NotEmpty
    private Set<String> centersAddresses;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageBase64;
}
