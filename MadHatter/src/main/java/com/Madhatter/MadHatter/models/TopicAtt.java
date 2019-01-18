package com.Madhatter.MadHatter.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="TOPIC_ATT")

public class TopicAtt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOPIC_ATT_SEQUENCE")
	private long id;
	
	@Column(name="ATTACHMENT_ID")
	private long attachmentId;
	
	@ManyToOne
	@JoinColumn(name="TOPIC_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private Topic topic;
	 
	@Column(name="FILE_NM")
	private String fileNm;
		
	@Column(name="FILE_SZ")
	private long fileSz;
		
	@Column(name="DESCRIPTION")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}


	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public long getFileSz() {
		return fileSz;
	}

	public void setFileSz(long fileSz) {
		this.fileSz = fileSz;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}