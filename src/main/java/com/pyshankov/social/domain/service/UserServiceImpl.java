package com.pyshankov.social.domain.service;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;
import com.pyshankov.social.domain.repository.PostRepository;
import com.pyshankov.social.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pyshankov on 15.04.16.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void addPostToUser(User u, Post p) {
        p.setOwner(u);
        postRepository.addPost(p);
    }

    @Override
    public List<Post> getAllPostOfUser(User u) {
        return userRepository.getUserPost(u);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getByUserId(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.getByUserName(name);
    }

    @Override
    public User updateUser(User u) {
        return updateUser(u);
    }
}
