package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.Post;

import java.util.List;

/**
 * Created by pyshankov on 28.01.2016.
 */
public interface PostRepository {

    long addPost(Post p);
    Post getById(long id);
    void deletePost(Post p);
    void updatePost(Post p);

}
