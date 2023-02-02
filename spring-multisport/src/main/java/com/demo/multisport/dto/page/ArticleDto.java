package com.demo.multisport.dto.page;


import com.demo.multisport.entities.page.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Builder
public class ArticleDto {

    @NonNull
    @Size(min = 4, max = 50)
    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = false)
    private String content;

    @NonNull
    @JsonIgnore
    private LocalDateTime publishedAt;

    @JsonProperty(required = false)
    private List<Comment> comments;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pictureBase64;

    public ArticleDto(@NonNull String title, @NonNull String content, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.publishedAt = LocalDateTime.now();
        this.comments = comments;
    }

    public ArticleDto(@NonNull String title, @NonNull String content) {
        this.title = title;
        this.content = content;
        this.publishedAt = LocalDateTime.now();
    }

}
