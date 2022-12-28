package com.demo.multisport.entities.page;

import com.demo.multisport.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NonNull
    @Column(length = 50, nullable = false)
    private String title;

    @NonNull
    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @NonNull
    @Column(name = "published_at", columnDefinition = "DATETIME DEFAULT NOW() NOT NULL")
    private LocalDateTime publishedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ARTICLE_PLAN"))
    private User author;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    public Article(String title, String content, Set<Comment> comments) {
            this.title = title;
            this.content = content;
            this.comments = comments;

            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH;:mm");
            this.publishedAt = LocalDateTime.parse(formatter.format(LocalDateTime.now()));
    }

    public Article(String title, String content, Set<Comment> comments, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.comments = comments;

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH;:mm");
        this.publishedAt = LocalDateTime.parse(formatter.format(publishedAt));
    }
}
