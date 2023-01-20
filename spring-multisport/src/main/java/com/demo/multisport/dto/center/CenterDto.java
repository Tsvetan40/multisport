package com.demo.multisport.dto.center;

import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RelaxCenter.class, name = "relaxCenter"),
        @JsonSubTypes.Type(value = SportCenter.class, name = "sportCenter")})
@Builder
public class CenterDto {

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

    @JsonProperty(required = false)
    @NonNull
    private Double rating;

    @JsonProperty(required = false)
    private Set<String> services;
}
