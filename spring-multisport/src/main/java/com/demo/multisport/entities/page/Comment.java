package com.demo.multisport.entities.page;


import com.demo.multisport.entities.User;
import com.demo.multisport.entities.center.Center;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NonNull
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String content;
    @NonNull
    @Column(columnDefinition = "DATETIME DEFAULT NOW() NOT NULL")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // maybe not that see if is part of the response
    private LocalDateTime publishedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENT_USE"))
    private User user;

    //can be null because a comment can be either for article or center
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_COMMENT_ARTICLE"))
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "center_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_COMMENT_CENTER"))
    private Center center;

    public Comment(String content, LocalDateTime publishedAt, User user, Article article, Center center) {
        if (article == null && center == null) {
            //to do throw exception
        }

        this.content = content;
        this.user = user;
        this.center = center;
        this.article = article;

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH;:mm");
        this.publishedAt = LocalDateTime.parse(formatter.format(publishedAt));
    }

    public Comment(String content, User user, Article article, Center center) {
        if (article == null && center == null) {
            //to do throw exception
        }

        this.content = content;
        this.user = user;
        this.center = center;
        this.article = article;

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH;:mm");
        this.publishedAt = LocalDateTime.parse(formatter.format(LocalDateTime.now()));
    }
}
