package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


/**
 * Created by pyshankov on 28.01.2016.
 */
public interface UserRepository {

    @CachePut(value="userCache", key="#result.id")
    User addUser(User user);
    void updateUser(User user);
    @CacheEvict(value = "userCache",key = "#result")
    long deleteUser(User user);
    User getByUserName(String username);
    @Cacheable(value = "userCache",key = "#id")
    User getByUserId(long id);
}
