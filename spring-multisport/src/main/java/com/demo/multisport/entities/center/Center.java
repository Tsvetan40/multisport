package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "center_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "centers")
@NoArgsConstructor
@Data
@AllArgsConstructor
public abstract class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String description;

    @ManyToMany(mappedBy = "centers")
    private Set<Plan> plans;

    @OneToMany(mappedBy = "center")
    private Set<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CENTER_RATING"))
    private Rating rating;
}
