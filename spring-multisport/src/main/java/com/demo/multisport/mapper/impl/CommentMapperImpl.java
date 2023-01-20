package com.demo.multisport.mapper.impl;

import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.entities.user.User;
import com.demo.multisport.mapper.CommentMapper;
import com.demo.multisport.services.page.PageService;
import com.demo.multisport.services.center.CenterService;
import com.demo.multisport.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {
    private final PageService pageService;
    private final UserService userService;
    private final CenterService centerService;

    @Autowired
    public CommentMapperImpl(PageService pageService, UserService userService, CenterService centerService) {
        this.pageService = pageService;
        this.userService = userService;
        this.centerService = centerService;
    }

    @Override
    public Comment commentDtoToComment(CommentDto commentDto) {

        Comment comment = Comment
                .builder()
                .publishedAt(commentDto.getPublishedAt())
                .content(commentDto.getContent())
                .user(userService.getUserByEmail(commentDto.getEmail()))
                .build();

        if (commentDto.getArticleTitle() != null && commentDto.getCenterAddress() == null) {
            comment.setArticle(pageService.getArticleByTitle(commentDto.getArticleTitle()).get());
        } else if (commentDto.getArticleTitle() == null && commentDto.getCenterAddress() != null) {
            comment.setCenter(centerService.getCenterByAddressAndType(commentDto.getCenterAddress(), commentDto.getTypeCenter()));
        } else {
            throw new IllegalStateException("Comment can't be on both article and center");
        }

        return comment;
    }

    @Override
    public CommentDto commentToCommentDto(Comment comment) {
        User user = comment.getUser();

        CommentDto commentDto = CommentDto
                .builder()
                .content(comment.getContent())
                .publishedAt(comment.getPublishedAt())
                .firstName(user.getFirstName())
                .lastName(user.getSecondName())
                .email(user.getEmail())
                .build();

        if (comment.getArticle() != null) {
            commentDto.setArticleTitle(comment.getArticle().getTitle());
        } else {
            commentDto.setCenterAddress(comment.getCenter().getAddress());
        }
        return commentDto;
    }
}
