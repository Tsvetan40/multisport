package com.demo.multisport.entities.page;

import com.demo.multisport.entities.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


//remove author for now
@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank()
    @Size(min = 4, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @NonNull
    @Column(name = "published_at", columnDefinition = "DATETIME DEFAULT NOW() NOT NULL")
    @JsonIgnore
    private LocalDateTime publishedAt;

//    @NotBlank()
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "author",  referencedColumnName = "id", nullable = false,
//                foreignKey = @ForeignKey(name = "FK_ARTICLE_USER"))
//    private User author;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    public Article(String title, String content, Set<Comment> comments) {
            this.title = title;
            this.content = content;
            this.comments = comments;

            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy HH::mm");
            this.publishedAt = LocalDateTime.parse(formatter.format(LocalDateTime.now()));
    }

    public Article(String title, String content, Set<Comment> comments, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.comments = comments;

        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy HH::mm");
        this.publishedAt = LocalDateTime.parse(formatter.format(publishedAt));
    }

    public Article withPublishedAt() {
        this.publishedAt = LocalDateTime.now();
        return this;
    }
}
