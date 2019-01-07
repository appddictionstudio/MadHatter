
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")

public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    
    @Column
    private String body;
    
    @Column
    private String parent_post;
    
    @Column
    private Date timestamp;

    public Posts() {}

    public Posts(String title, String body, String parent_post, Date timestamp) {
    	this.title = title;
    	this.body = body;
    	this.parent_post = parent_post;
    	this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getParentPost() {
        return parent_post;
    }

    public void setParentPost(String parent_post) {
        this.parent_post = parent_post;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}