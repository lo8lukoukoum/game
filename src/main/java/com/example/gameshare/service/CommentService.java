package com.example.gameshare.service;

import com.example.gameshare.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Comment comment);

    List<Comment> findCommentsByGameId(Integer gameId);

    Comment findCommentById(Integer id);

    void deleteComment(Integer id);

    List<Comment> findCommentsByUserId(Integer userId);
}
