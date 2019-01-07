
package com.Madhatter.MadHatter.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community")

public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    
    @Column
    private String description;
    
    @Column
    private Long topic;

    public Community() {}

    public Community(Long topic, String title, String description) {
    	this.topic = topic;
    	this.title = title;
    	this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCertName() {
        return topic;
    }

    public void setCertName(Long topic) {
        this.topic = topic;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}