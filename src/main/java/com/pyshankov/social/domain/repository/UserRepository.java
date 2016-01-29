package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;

import java.util.List;


/**
 * Created by pyshankov on 28.01.2016.
 */
public interface UserRepository {

    long addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getByUserName(String username);
    User getByUserId(long id);
}
