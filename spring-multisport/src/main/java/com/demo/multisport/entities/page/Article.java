package com.demo.multisport.entities.page;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 50)
    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @NonNull
    @Column(name = "published_at", columnDefinition = "DATETIME DEFAULT NOW() NOT NULL")
    private LocalDateTime publishedAt;

    @NotBlank
    @NonNull
    @Column(name = "path_file")
    private String pathFile;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

    public Article(@NonNull String title,@NonNull String content, Set<Comment> comments) {
            this.title = title;
            this.content = content;
            this.comments = comments;
            this.publishedAt = LocalDateTime.now();
    }

    public Article(@NonNull String title,@NonNull String content, Set<Comment> comments,@NonNull LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.publishedAt = publishedAt;
    }

    public Article withPublishedAt() {
        this.publishedAt = LocalDateTime.now();
        return this;
    }
}
