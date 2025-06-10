package com.example.gameshare.service.impl;

import com.example.gameshare.model.Collection;
import com.example.gameshare.repository.CollectionMapper;
import com.example.gameshare.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionMapper collectionMapper;

    @Autowired
    public CollectionServiceImpl(CollectionMapper collectionMapper) {
        this.collectionMapper = collectionMapper;
    }

    @Override
    public Collection addToCollection(Collection collection) {
        if (collectionMapper.findByUserIdAndGameId(collection.getUserId(), collection.getGameId()) != null) {
            throw new RuntimeException("游戏已在收藏中。");
        }
        collection.setCollectedAt(LocalDateTime.now());
        collectionMapper.insert(collection);
        // Fetch again to populate User and Game objects from joins
        return collectionMapper.findByUserIdAndGameId(collection.getUserId(), collection.getGameId());
    }

    @Override
    public void removeFromCollection(Integer userId, Integer gameId) {
        Collection existingCollection = collectionMapper.findByUserIdAndGameId(userId, gameId);
        if (existingCollection == null) {
            throw new RuntimeException("收藏记录未找到。");
        }
        collectionMapper.deleteByUserIdAndGameId(userId, gameId);
    }

    @Override
    public List<Collection> getUserCollection(Integer userId) {
        return collectionMapper.findByUserId(userId);
    }

    @Override
    public boolean isGameInUserCollection(Integer userId, Integer gameId) {
        return collectionMapper.findByUserIdAndGameId(userId, gameId) != null;
    }
}
