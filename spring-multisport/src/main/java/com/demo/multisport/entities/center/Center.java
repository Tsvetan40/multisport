package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "center_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "centers")
@Getter
@Setter
public abstract class Center implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @NotNull
    @Size(min = 10, max = 40)
    @Column(nullable = false, length = 40, unique = true)
    private String address;

    @NotNull
    @Size(min = 4)
    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;

    // check length
    @NotNull
    @ElementCollection
    @Column(columnDefinition = "VARCHAR(512) NOT NULL")
    private Set<String> pictures;

    @JsonBackReference
    @ManyToMany(mappedBy = "centers")
    private Set<Plan> plans;

    @JsonManagedReference
    @OneToMany(mappedBy = "center")
    private Set<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "rating_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_CENTER_RATING"))
    @JsonManagedReference
    private Rating rating;

    protected Center() { }

}
