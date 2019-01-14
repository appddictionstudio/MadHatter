
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
    
    
    @Column
    private String topicTitle;

	@Column
    private String files;
    
    @Column
    private String Quizzes;
    

    public Topic() {}

    public Topic(String files, String Quizzes, String topicTitle) {
    	this.topicTitle = topicTitle;
    	this.files = files;
    	this.Quizzes = Quizzes;
    	
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

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopic(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	
    
}