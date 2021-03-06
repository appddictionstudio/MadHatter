
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "topic")

public class Topic {

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOPIC_SEQUENCE")
    private Long id;

    @ManyToOne
	@JoinColumn(name="MOD_ID", referencedColumnName="ID", nullable=false)
	private Modules mod;

	@Column
    private String topicTitle;

	@Column
    private long topicOrder;

	@Column
    private boolean hidden;

	@OneToMany(cascade=CascadeType.MERGE, orphanRemoval = true)
	@JoinColumn(name = "topic_id")
	private List<TopicAtt> attachments;


    public Topic() {}

    public Topic( long topicOrder, boolean hidden, String topicTitle) {
    	this.topicTitle = topicTitle;
    	this.topicOrder = topicOrder;
    	this.hidden = hidden;
    }

    public Topic(long topicOrder, boolean hidden, String topicTitle, Modules mod, List<TopicAtt> attachments) {
    	this.topicTitle = topicTitle;
    
    	this.topicOrder = topicOrder;
    	this.hidden = hidden;
    	this.mod = mod;
    	this.attachments = attachments;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modules getMod() {
		return mod;
	}

	public void setMod(Modules mod) {
		this.mod = mod;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public long getTopicOrder() {
		return topicOrder;
	}

	public void setTopicOrder(long topicOrder) {
		this.topicOrder = topicOrder;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public List<TopicAtt> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TopicAtt> attachments) {
		this.attachments = attachments;
	}
}
