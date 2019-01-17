
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "topic")

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
	@JoinColumn(name="MOD_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private Modules mod;
    
	@Column
    private String topicTitle;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    @JsonManagedReference
    private List<File> files;

    
	@Column
    private String Quizzes;
	
	@Column
    private String hidden;
	
	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "topic_id")
	private List<TopicAtt> attachments;
    

    public Topic() {}

    public Topic(List<File> files, String Quizzes, String hidden, String topicTitle) {
    	this.topicTitle = topicTitle;
    	this.files = files;
    	this.Quizzes = Quizzes;
    	this.hidden = hidden;
    }
    
    public Topic(List<File> files, String Quizzes, String hidden, String topicTitle, Modules mod, List<TopicAtt> attachments) {
    	this.topicTitle = topicTitle;
    	this.files = files;
    	this.Quizzes = Quizzes;
    	this.hidden = hidden;
    	this.mod = mod;
    	this.attachments = attachments;
    }
    

  

	public List<TopicAtt> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TopicAtt> attachments) {
		this.attachments = attachments;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getQuizzes() {
		return Quizzes;
	}

	public void setQuizzes(String quizzes) {
		Quizzes = quizzes;
	}
	
	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopic(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	
	public Modules getMod() {
			return mod;
		}

	public void setMod(Modules mod) {
			this.mod = mod;
		}

	public void setTopicTitle(String topicTitle) {
			this.topicTitle = topicTitle;
		}

    
}