package com.example.gameshare.repository;

import com.example.gameshare.model.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectionMapper {

    List<Collection> findByUserId(Integer userId);

    Collection findByUserIdAndGameId(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    int insert(Collection collection);

    int delete(Integer id);

    int deleteByUserIdAndGameId(@Param("userId") Integer userId, @Param("gameId") Integer gameId);
}
