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
	
	@Column(name="ATTACHMENT_ID")
	private long attachmentId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="TOPIC_ATT_ID", referencedColumnName="ID")
	private TopicAtt topicatt;
	 
	@Column(name="FILE_NM")
	private String fileNm;
		
	@Column(name="FILE_SZ")
	private long fileSz;
		
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="GRADEN")
	private long grade_numerator;
	
	@Column(name="GRADED")
	private long grade_denominator;
	
	@ManyToOne
	@JoinColumn(name="STUDENT_ID", referencedColumnName="ID")
	private User student;

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
	
	public long getGradeN() {
		return grade_numerator;
	}

	public void setGradeN(long grade_numerator) {
		this.grade_numerator = grade_numerator;
	}
	
	public long getGradeD() {
		return grade_denominator;
	}

	public void setGradeD(long grade_denominator) {
		this.grade_denominator = grade_denominator;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	
}