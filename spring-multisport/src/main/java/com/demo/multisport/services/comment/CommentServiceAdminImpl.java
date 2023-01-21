package com.demo.multisport.services.comment;

import com.demo.multisport.dao.CommentRepository;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.page.Comment;
import com.demo.multisport.exceptions.comment.CommentNotFoundException;
import com.demo.multisport.mapper.impl.CommentMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceAdminImpl implements CommentService{
    private final CommentMapperImpl commentMapper;
    private final CommentRepository commentRepository;

    @Override
    public CommentDto deleteComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);

        try {
            commentRepository.delete(comment);
        } catch (Exception e) {
            throw new CommentNotFoundException("Comment doesn't exist " + comment);
        }

        return commentDto;
    }

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentRepository.save(comment);

        return commentDto;
    }
}
