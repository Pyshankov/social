package com.pyshankov.social.domain.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @SequenceGenerator(name="generator", sequenceName="users_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="generator")
    @Column(name = "id",unique = true,nullable = false)
    private long id;
    @Column(name = "username",unique = true,nullable = false,length = 20)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",unique = true,nullable = false,length = 30)
    private String email;
    @Column(name = "role",nullable = false,length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="follow",
            joinColumns = @JoinColumn( name="id_follower"),
            inverseJoinColumns = @JoinColumn( name="id_following")
    )
    private List<User> followingUsers;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="follow",
            joinColumns = @JoinColumn( name="id_following"),
            inverseJoinColumns = @JoinColumn( name="id_follower")
    )
    private List<User> userFollowers;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post> itemList;

    public User(){}

    private User(UserBuilder userBuilder){
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.role = userBuilder.role;
        this.followingUsers=userBuilder.followingUsers;
        this.userFollowers=userBuilder.userFollowers;
        this.itemList=userBuilder.itemList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<User> getFollowingUsers() {
        return followingUsers;
    }

    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public List<User> getUserFollowers() {
        return userFollowers;
    }

    public void setUserFollowers(List<User> userFollowers) {
        this.userFollowers = userFollowers;
    }

    public List<Post> getItemList() {
        return itemList;
    }

    public void setItemList(List<Post> itemList) {
        this.itemList = itemList;
    }

    public void addPost(Post post){
        post.setOwner(this);
        this.itemList.add(post);
    }

    public void addSubscriber(User user){
        if(!this.userFollowers.contains(user))
        this.userFollowers.add(user);

    }

    public void addFolowing(User user){
        if(!this.followingUsers.contains(user))
        this.followingUsers.add(user);
    }

    public static class UserBuilder {
        //required
        private String username;
        private String password;
        private String email;
        //optional
        private Role role=Role.USER;
        private List<User> followingUsers=new ArrayList<User>();
        private List<User> userFollowers=new ArrayList<User>();
        private List<Post> itemList=new ArrayList<Post>();

        public UserBuilder(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public UserBuilder role(Role r) {
            this.role = r;
            return this;
        }

        public UserBuilder followingUsers(Collection<User> followingUsers) {
            this.followingUsers.addAll(followingUsers);
            return this;
        }

        public UserBuilder userFollowers(Collection<User> userFollowers) {
            this.userFollowers.addAll(userFollowers);
            return this;
        }

        public UserBuilder itemList(Collection<Post> itemList) {
            this.itemList.addAll(itemList);
            return this;
        }
        public User build(){
            return new User(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id==user.id&&username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
