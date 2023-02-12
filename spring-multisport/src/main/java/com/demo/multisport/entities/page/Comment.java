package com.demo.multisport.entities.page;


import com.demo.multisport.entities.user.User;
import com.demo.multisport.entities.center.Center;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;



@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,  property = "id")
public class Comment implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Size(max = 255)
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String content;

    @NonNull
    @Column(columnDefinition = "DATETIME DEFAULT NOW() NOT NULL")
    private LocalDateTime publishedAt;

    @JsonBackReference
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,
                foreignKey = @ForeignKey(name = "FK_COMMENT_USER"))
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_COMMENT_ARTICLE"))
    private Article article;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "center_address", referencedColumnName = "address",
                foreignKey = @ForeignKey(name = "FK_COMMENT_CENTER"))
    private Center center;

}
