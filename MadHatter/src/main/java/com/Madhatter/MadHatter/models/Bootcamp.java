
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bootcamp")

public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    
    @Column
    private String description;

    @Column
    private Date start_date;

    @Column
    private Date end_date;

    @Column
    private Long topic;

    public Bootcamp() {}

    public Bootcamp(String title, String description, Date start_date, Date end_date, Long topic) {
    	this.title = title;
    	this.description = description;
    	this.start_date = start_date;
    	this.end_date = end_date;
    	this.topic = topic;
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
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }
    
    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }
    
    public Long getTopic() {
        return topic;
    }

    public void setTopic(Long topic) {
        this.topic = topic;
    }
}