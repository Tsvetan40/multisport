package com.demo.multisport.services.comment;

import com.demo.multisport.dto.page.CommentDto;

public interface CommentService {
    CommentDto deleteComment(CommentDto commentDto);
    CommentDto addComment(CommentDto commentDto);
}
