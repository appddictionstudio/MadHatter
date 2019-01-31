
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

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
    
	@Column
	private String bootcamp;
	
	@Column
    private long progress;
	
	@OneToMany(mappedBy="mod", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Topic> topic;
	
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "MOD_ID")
	private List<ModAtt> modAttachments;

	@OneToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name = "mod_to_resource_id")
	private List<Resources> resources;
	
    public Modules() {}

    public Modules(String title, long progress, String description, String iconLink, String bootcamp) {
    	this.title = title;
    	this.description = description;
    	this.iconLink = iconLink;
    	this.progress = progress;
    	this.bootcamp = bootcamp;
    }
    
	public List<ModAtt> getModAttachments() {
		return modAttachments;
	}

	public void setModAttachments(List<ModAtt> modAttachments) {
		this.modAttachments = modAttachments;
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
	
	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
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

	public String getBootcamp() {
		return bootcamp;
	}

	public void setBootcamp(String bootcamp) {
		this.bootcamp = bootcamp;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	

}