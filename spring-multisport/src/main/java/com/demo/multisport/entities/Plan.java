package com.demo.multisport.entities;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 4, max=20)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNIQUE")
    private String name;

    @Min(10)
    private double price;


    @OneToMany(mappedBy = "plan")
    private List<User> subscribedUsers;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
        name = "plans_centers",
            joinColumns = @JoinColumn(name = "plan_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "center_id", nullable = false)
    )
    private Set<Center> centers;

    @NonNull
    @NotBlank
    @Column(name = "path_file")
    private String pathFile;

}
