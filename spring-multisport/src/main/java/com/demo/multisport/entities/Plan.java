package com.demo.multisport.entities;

import com.demo.multisport.entities.center.Center;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
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
    @NonNull
    private String name;
    private double price;

    @OneToMany(mappedBy = "plan")
    private Set<User> subscribedUsers;

    @ManyToMany
    @JoinTable(
        name = "plans_centers",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "center_id")
    )
    private Set<Center> centers;
}
