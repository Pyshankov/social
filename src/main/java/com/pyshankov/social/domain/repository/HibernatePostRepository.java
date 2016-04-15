package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;


/**
 * Created by pyshankov on 28.01.2016.
 */
@Transactional(value = "txGreeting")
@Repository
public class HibernatePostRepository implements  PostRepository {

    private SessionFactory sessionFactory;

    @Autowired
    HibernatePostRepository(SessionFactory s){
        this.sessionFactory=s;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public long addPost(Post p) {
        Serializable id = currentSession().save(p);
        return (Long)id;
    }

    public Post getById(long id) {
        return (Post) currentSession().get(Post.class,id);
    }
    public void deletePost(Post p) {
        currentSession().delete(p);
    }

    public void updatePost(Post p) {
        currentSession().update(p);
    }

    @Override
    public List<Post> getAllPostOfUser(User u) {
        return currentSession().createQuery("select p from Post p where p.owner=:u")
                .setParameter("u", u).list();
    }


}
