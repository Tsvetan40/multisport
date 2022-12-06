package com.demo.multisport.entities;

import com.demo.multisport.entities.page.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
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

    @NotBlank
    @Size(min = 4, max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 20)
    @Column(name = "second_name", length = 20, nullable = false)
    private String secondName;

    //change later length after hashing
    @NotBlank
    @Size(min = 10, max = 40)
    @Column(length = 40, nullable = false, unique = true)
    private String email;

    //change later when hashing same min and max
    @NotBlank
    @Size(min = 10, max = 40)
    @Column(nullable = false, length = 40)
    private String password;

    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_PLAN"))
    private Plan plan;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    public User(@NonNull String firstName, @NonNull String secondName,@NonNull String email,@NonNull String password, int age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.plan = null;
        this.comments = new HashSet<>();
    }


}
