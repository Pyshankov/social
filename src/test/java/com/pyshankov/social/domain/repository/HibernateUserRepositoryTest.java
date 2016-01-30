package com.pyshankov.social.domain.repository;



import com.pyshankov.social.config.RootConfig;
import com.pyshankov.social.domain.entity.Post;
import com.pyshankov.social.domain.entity.Role;
import com.pyshankov.social.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class})
public class HibernateUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void addDeleteUser_test(){
        User user = new User.UserBuilder("pyshankov","123321","pyshankov@gmail.com").role(Role.ADMIN).build();
        userRepository.addUser(user);
        user = userRepository.getByUserId(1);

        Post user_post1 = new Post("best!","photo1.png",null,null);
        Post user_post2 = new Post("best2!","photo2.png",null,null);
        user.addPost(user_post1);
        user.addPost(user_post2);
        userRepository.updateUser(user);
        userRepository.deleteUser(user);
    }
    @Test
    public void subscription_test() {
        User user = new User.UserBuilder("pyshankov", "123321", "pyshankov@gmail.com").role(Role.ADMIN).build();
        User user2 = new User.UserBuilder("vasia", "vasia", "vasia@gmail.com").build();
        User user3 = new User.UserBuilder("petia", "petia", "petia@gmail.com").build();

        userRepository.addUser(user);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        user.addFolowing(user2);
        user.addFolowing(user3);

        userRepository.updateUser(user);
        userRepository.updateUser(user2);
        user2.addSubscriber(user);

        userRepository.deleteUser(user);
        userRepository.deleteUser(user2);
        userRepository.deleteUser(user3);



    }

}
