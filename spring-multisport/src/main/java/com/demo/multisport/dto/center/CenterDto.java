package com.demo.multisport.dto.center;

import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.Plan;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.TypeCenter(value = RelaxCenter.class, name = "relaxCenter"),
//        @JsonSubTypes.TypeCenter(value = SportCenter.class, name = "sportCenter")})
@Builder
public class CenterDto {

    @NonNull
    @JsonProperty(required = false)
    @Positive
    private Long id;

    @NonNull
    @JsonProperty(required = true)
    private TypeCenter centerType;

    @NonNull
    @JsonProperty(required = true)
    @Size(min = 4, max = 20)
    private String name;

    @NonNull
    @JsonProperty(required = true)
    @Size(min = 10, max = 40)
    private String address;

    @NonNull
    @JsonProperty(required = true)
    @Size(min = 4)
    private String description;

    @NonNull
    @NotEmpty
    @JsonProperty(required = true)
    private Set<String> pictures;

    @JsonProperty(required = false)
    private Set<Plan> plans;

    @JsonProperty(required = false)
    private Set<CommentDto> comments;

    @JsonProperty(required = true)
    @NonNull
    private Double rating;

    @JsonProperty(required = false)
    private Set<String> services;
}
