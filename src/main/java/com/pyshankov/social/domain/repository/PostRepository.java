package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;

import java.util.List;

/**
 * Created by pyshankov on 28.01.2016.
 */
public interface PostRepository {

    long addPost(Post p);
    Post getById(long id);
    void deletePost(Post p);
    void updatePost(Post p);
    List<Post> getAllPostOfUser(User u);

}
