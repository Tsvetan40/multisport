package com.demo.multisport.dto;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PlanDto {

    @NonNull
    @Size(min = 4, max = 20)
    private String name;

    @NonNull
    @Min(10)
    private Double price;

    private Set<User> users;

    @NonNull
    @NotEmpty
    private Set<String> centersAddresses;

}
