
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
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
    
    
    public Modules getMod() {
		return mod;
	}

	public void setMod(Modules mod) {
		this.mod = mod;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	@Column
    private String topicTitle;

	@Column
    private String files;
    
	@Column
    private String Quizzes;
	
	@Column
    private String hidden;
    

    public Topic() {}

    public Topic(String files, String Quizzes, String hidden, String topicTitle) {
    	this.topicTitle = topicTitle;
    	this.files = files;
    	this.Quizzes = Quizzes;
    	this.hidden = hidden;
    	
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
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
	
    
}