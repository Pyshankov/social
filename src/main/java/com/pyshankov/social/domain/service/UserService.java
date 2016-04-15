package com.pyshankov.social.domain.service;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;

import java.util.List;

/**
 * Created by pyshankov on 15.04.16.
 */
public interface UserService {
    void addPostToUser(User u, Post p);
    List<Post> getAllPostOfUser(User u);
    User getUserById(long id);
    User getUserByName(String name);
    User updateUser(User u);
}
