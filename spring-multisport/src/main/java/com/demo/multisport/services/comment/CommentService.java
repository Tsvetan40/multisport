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
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;


    public void addComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentRepository.save(comment);
    }

}
