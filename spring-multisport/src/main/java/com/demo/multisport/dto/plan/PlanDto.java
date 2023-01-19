package com.demo.multisport.dto.plan;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.user.User;
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
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Builder
public class PlanDto {

    @NonNull
    @Size(min = 4, max = 20)
    @JsonProperty(required = true)
    private String name;

    @NonNull
    @Min(10)
    @JsonProperty(required = true)
    private Double price;

    @JsonProperty(required = false)
    private Set<User> users;

    @NonNull
    @NotEmpty
    @JsonProperty(required = true)
    private Set<Center> centers;


}
