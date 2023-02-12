package com.demo.multisport.entities.page;

import com.demo.multisport.entities.center.Center;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Table(name = "ratings")
@RequiredArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_ABSENT)
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NonNull
    @Min(1)
    @Max(5)
    private Double rate;

    @NonNull
    @OneToOne(mappedBy = "rating")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    private Center center;

    public Rating () {
        this.rate = 5.0;
    }
}
