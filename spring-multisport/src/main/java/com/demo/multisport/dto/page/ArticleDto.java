package com.demo.multisport.dto.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Size(min = 4, max = 50)
    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = false)
    private String content;

    @NonNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedAt;

    @JsonProperty(required = false)
    private List<CommentDto> comments;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String pictureBase64;

    public ArticleDto(@NonNull String title, @NonNull String content, List<CommentDto> comments) {
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
