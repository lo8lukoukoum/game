package com.example.gameshare.service;

import com.example.gameshare.model.Collection;

import java.util.List;

public interface CollectionService {

    Collection addToCollection(Collection collection);

    void removeFromCollection(Integer userId, Integer gameId); // Changed from Integer id to userId, gameId

    List<Collection> getUserCollection(Integer userId);

    boolean isGameInUserCollection(Integer userId, Integer gameId);
}
