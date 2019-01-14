
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Clob;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "modules")

public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String title;
    
    @Column
    private String description;

	@Column
    private String iconLink;
    
    @OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "mod_id")
	private List<Topic> topic;
	
    public Modules() {}

    public Modules(String title, String description, String iconLink) {
    	this.title = title;
    	this.description = description;
    	this.iconLink = iconLink;
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
    
    public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public List<Topic> getTopicId() {
		return topic;
	}

	public void setTopicId(List<Topic> topic) {
		this.topic = topic;
	}
	

}