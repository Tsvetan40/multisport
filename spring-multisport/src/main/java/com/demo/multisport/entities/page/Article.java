package com.demo.multisport.entities.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
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
