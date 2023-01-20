package com.demo.multisport.mapper;


import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.page.Comment;
import org.mapstruct.Mapper;

public interface CommentMapper {
    Comment commentDtoToComment(CommentDto commentDto);
    CommentDto commentToCommentDto(Comment comment);
}
