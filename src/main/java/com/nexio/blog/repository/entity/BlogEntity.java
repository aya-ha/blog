package com.nexio.blog.repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BLOG")
public class BlogEntity implements Serializable {

    private static final long serialVersionUID = -5459908560813401957L;

    @Column(name="ID")
    @Id
    private int id;

    @Column(name="COMMENT")
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
