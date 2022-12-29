package com.demo.multisport.entities.user;

import com.demo.multisport.entities.Plan;
import com.demo.multisport.entities.page.Article;
import com.demo.multisport.entities.page.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude( value = JsonInclude.Include.NON_EMPTY)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnore
    @Size(min = 10, max = 10)
    @Column(columnDefinition = "CHAR(10) NOT NULL")
    private String salt;

    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_PLAN"))
    private Plan plan;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

//    @OneToMany(mappedBy = "author")
//    private Set<Article> articles;

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
