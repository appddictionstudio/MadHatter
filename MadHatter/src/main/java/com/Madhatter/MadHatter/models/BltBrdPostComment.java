package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="BLT_BRD_POST_COMMENT")
@SequenceGenerator(name = "BLT_BRD_POST_COMMENT_SEQUENCE", sequenceName = "BLT_BRD_POST_COMMENT_SEQ", allocationSize = 1)
public class BltBrdPostComment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_POST_SEQUENCE")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "POST_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private BltBrdPost post;
	
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID", referencedColumnName="ID", nullable=false)
	private User author;
	
	@Lob
	private String text;
	
	@Column(name="POST_DATE")
	private Timestamp postDate;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BltBrdPost getPost() {
		return post;
	}

	public void setPost(BltBrdPost post) {
		this.post = post;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	
	

}
