package com.pyshankov.social.domain.repository;

import com.pyshankov.social.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by pyshankov on 28.01.2016.
 */
@Transactional(value = "txGreeting")
@Repository
public class HibernateUserRepository implements UserRepository {

    private SessionFactory sessionFactory;

    private PostRepository postRepository;

    @Autowired
    HibernateUserRepository(SessionFactory s,PostRepository p){
        this.sessionFactory=s;
        this.postRepository=p;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User addUser(User user) {
        user.setId((Long)currentSession().save(user));
        return user;
    }

    public void updateUser(User user) {
        currentSession().update(user);
    }

    public long deleteUser(User user) {
        long id = user.getId();
        currentSession().delete(user);
        return id;
    }


    public User getByUserName(String username) {
        return (User) currentSession()
                .createQuery("select u from User u where u.username=:user_name")
                .setParameter("user_name", username)
                .list().get(0);
    }

    public User getByUserId(long id) {
        return currentSession().get(User.class,id);
    }


}
