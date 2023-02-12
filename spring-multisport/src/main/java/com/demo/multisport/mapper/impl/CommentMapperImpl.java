package com.demo.multisport.mapper.impl;

import com.demo.multisport.dao.ArticleRepository;
import com.demo.multisport.dao.CenterRepository;
import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.exceptions.user.UserNotFoundException;
import com.demo.multisport.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.InvalidParameterException;


@Component
public class CommentMapperImpl  implements CommentMapper {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CenterRepository centerRepository;
    private final static String SPORT_CENTER_TYPE = "SportCenter";
    private final static String RELAX_CENTER_TYPE = "RelaxCenter";

    @Autowired
    public CommentMapperImpl(UserRepository userRepository, ArticleRepository articleRepository, CenterRepository centerRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.centerRepository = centerRepository;
    }

    @Override
    public Comment commentDtoToComment(CommentDto commentDto) {
         Comment comment = Comment
                  .builder()
                  .content(commentDto.getContent())
                  .publishedAt(commentDto.getPublishedAt())
                  .user(userRepository.findUserByEmail(commentDto.getEmail())
                          .orElseThrow(() -> new UserNotFoundException("Cannot map commentDto to Comment due to user")))
                  .build();

        if (commentDto.getArticleTitle() != null && commentDto.getCenterAddress() == null) {
            comment.setArticle(articleRepository.getArticleByTitle(commentDto.getArticleTitle())
                    .orElseThrow(() -> new InvalidParameterException("Comment doesn't have article " + commentDto)));
        } else if (commentDto.getArticleTitle() == null && commentDto.getCenterAddress() != null) {
            comment.setCenter(centerRepository.getCenterByAddressAndType(commentDto.getCenterAddress(), commentDto.getTypeCenter())
                    .orElseThrow(() ->  new InvalidParameterException("Comment doesn't have center " + commentDto)));
        } else {
            throw new InvalidParameterException("Invalid parameter: " + commentDto);
        }

        return comment;
    }

    @Override
    public CommentDto commentToCommentDto(Comment comment) {
        User user = comment.getUser();
        return CommentDto
                .builder()
                .content(comment.getContent())
                .publishedAt(comment.getPublishedAt())
                .firstName(user.getFirstName())
                .lastName(user.getSecondName())
                .email(user.getEmail())
                .articleTitle(
                  comment.getArticle() != null ? comment.getArticle().getTitle() : null
                )
                .centerAddress(
                  comment.getCenter() != null ? comment.getCenter().getAddress() : null
                )
                .typeCenter(comment.getCenter() instanceof SportCenter ? SPORT_CENTER_TYPE : RELAX_CENTER_TYPE)
                .build();
    }
}
