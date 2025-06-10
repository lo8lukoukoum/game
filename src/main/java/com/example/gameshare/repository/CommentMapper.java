package com.example.gameshare.repository;

import com.example.gameshare.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    Comment findById(Integer id);

    List<Comment> findByGameId(Integer gameId);

    List<Comment> findByUserId(Integer userId);

    int insert(Comment comment);

    int delete(Integer id);
}
