package com.demo.multisport.services.comment;

import com.demo.multisport.dao.CommentRepository;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = commentRepository.save(commentMapper.commentDtoToComment(commentDto));
        return commentMapper.commentToCommentDto(comment);
    }
}
