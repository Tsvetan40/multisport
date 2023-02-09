package com.demo.multisport.entities.user;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Comment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
@Builder
public class User implements Serializable {
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


    @NotBlank
    @Size(min = 10, max = 40)
    @Column(length = 40, nullable = false, unique = true)
    private String email;


    @NotBlank
    @Size(min = 10, max = 26)
    @Column(columnDefinition = "CHAR(26) NOT NULL")
    private String password;


    @Size(min = 10, max = 10)
    @Column(columnDefinition = "CHAR(10) NOT NULL")
    private String salt;

    @Positive
    private int age;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status  status;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_PLAN"))
    private Plan plan;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
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

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return  this;
    }

}
