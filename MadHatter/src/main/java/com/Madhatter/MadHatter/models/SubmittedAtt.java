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
@Table(name="SUBMITTED_ATT")

public class SubmittedAtt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMITTED_ATT_SEQUENCE")
	private long id;
	
	@Column(name="SUBMITTEDATTACHMENT_ID")
	private long subAttachmentId;
	
	@ManyToOne
	@JoinColumn(name="TOPIC_ATT_ID", referencedColumnName="ID", nullable=false)
	private TopicAtt topicatt;
	 
	@Column(name="FILE_NM")
	private String fileNm;
		
	@Column(name="FILE_SZ")
	private long fileSz;
		
	@Column(name="DESCRIPTION")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="STUDENT_ID", referencedColumnName="ID", nullable=false)
	private User student;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSubAttachmentId() {
		return subAttachmentId;
	}

	public void setSubAttachmentId(long subAttachmentId) {
		this.subAttachmentId = subAttachmentId;
	}

	public TopicAtt getTopicatt() {
		return topicatt;
	}

	public void setTopicatt(TopicAtt topicatt) {
		this.topicatt = topicatt;
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

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	
}