package com.demo.multisport.entities.center;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.page.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 4, max = 20)
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @NotBlank
    @Size(min = 10, max = 40)
    @Column(columnDefinition = "VARCHAR(40) NOT NULL")
    private String address;
    @NotBlank
    @Size(min = 4, max = 255)
    @Column(columnDefinition = "NOT NULL")
    private String description;

    @ManyToMany(mappedBy = "centers")
    private Set<Plan> plans;

    @OneToMany(mappedBy = "center")
    private Set<Comment> comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_CENTER_RATING"), columnDefinition = "NOT NULL")
    private Rating rating;
}
