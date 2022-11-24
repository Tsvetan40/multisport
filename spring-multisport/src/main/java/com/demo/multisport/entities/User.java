package com.demo.multisport.entities;

import com.demo.multisport.entities.page.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_PLAN"))
    private Plan plan;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
}
