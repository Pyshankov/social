package com.pyshankov.social.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @SequenceGenerator(name="generator", sequenceName="posts_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="generator")
    @Column(name = "id",unique = true,nullable = false)
    private long id;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "photo")
    private String photo;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName="id")
    private User owner;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="likes",
            joinColumns={@JoinColumn(name="id_post")},
            inverseJoinColumns={@JoinColumn(name="id_user")})
    private List<User> whoLiked;


    public Post(){}

    public Post(String content, String photo, Timestamp date, List<User> whoLiked) {
        this.content = content;
        this.photo = photo;
        this.date = date;
        this.whoLiked = whoLiked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<User> getWhoLiked() {
        return whoLiked;
    }

    public void setWhoLiked(List<User> whoLiked) {
        this.whoLiked = whoLiked;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
