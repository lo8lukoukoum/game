package com.example.gameshare.service.impl;

import com.example.gameshare.model.Comment;
import com.example.gameshare.repository.CommentMapper;
import com.example.gameshare.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Comment createComment(Comment comment) {
        comment.set创建时间(LocalDateTime.now());
        comment.set更新时间(LocalDateTime.now());
        commentMapper.insert(comment);
        // To get the user details populated from the join in XML, we might need to fetch it again.
        // Or adjust the insert statement to return the full object (less common with MyBatis for inserts).
        return commentMapper.findById(comment.getId());
    }

    @Override
    public List<Comment> findCommentsByGameId(Integer gameId) {
        return commentMapper.findByGameId(gameId);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentMapper.findById(id);
    }

    @Override
    public void deleteComment(Integer id) {
        commentMapper.delete(id);
    }

    @Override
    public List<Comment> findCommentsByUserId(Integer userId) {
        return commentMapper.findByUserId(userId);
    }
}
