package com.wildadventures.mscomments.services;

import com.wildadventures.mscomments.controllers.dto.CommentDto;
import com.wildadventures.mscomments.dao.entity.Comment;
import com.wildadventures.mscomments.exceptions.TechnicalException;

import java.util.List;

public interface CommentService {
    Comment writeNewComment(Comment comment) throws TechnicalException;
    void removeComment(Comment comment) throws TechnicalException;
    void loadWriters(List<CommentDto> comments);
}
