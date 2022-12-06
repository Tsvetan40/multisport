package com.demo.multisport.entities;

import com.demo.multisport.entities.center.Center;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @Size(min = 4)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @Min(10)
    private double price;

    @OneToMany(mappedBy = "plan")
    private Set<User> subscribedUsers;

    @ManyToMany
    @JoinTable(
        name = "plans_centers",
            joinColumns = @JoinColumn(name = "plan_id", columnDefinition = "NOT NULL"),
            inverseJoinColumns = @JoinColumn(name = "center_id", columnDefinition = "NOT NULL")
    )
    private Set<Center> centers;
}
