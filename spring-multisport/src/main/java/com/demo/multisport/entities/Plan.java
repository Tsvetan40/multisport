package com.demo.multisport.entities;

import com.demo.multisport.entities.center.Center;
import com.demo.multisport.entities.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(min = 4, max=20)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @Min(10)
    private double price;

    @OneToMany(mappedBy = "plan")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<User> subscribedUsers;

    @ManyToMany
    @JoinTable(
        name = "plans_centers",
            joinColumns = @JoinColumn(name = "plan_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "center_id", nullable = false)
    )
    private Set<Center> centers;
}
