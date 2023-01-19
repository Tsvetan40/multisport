package com.demo.multisport.entities.page;

import com.demo.multisport.entities.center.Center;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NonNull
    private Double rate;

    @NonNull
    @OneToOne(mappedBy = "rating")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Center center;
}
