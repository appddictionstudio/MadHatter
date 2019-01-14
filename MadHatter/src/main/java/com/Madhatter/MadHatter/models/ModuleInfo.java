package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "moduleinfo")

public class ModuleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column
    private String topictitle;
    
    @Column
    private String description;


    public ModuleInfo() {}

    public ModuleInfo(String topictitle, String description) {
    	this.topictitle = topictitle;
    	this.description = description;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTopicTitle() {
        return topictitle;
    }

    public void setTopicTitle(String topictitle) {
        this.topictitle = topictitle;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

}