package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;




/**
 * The persistent class for the BLT_BRD_ACT database table.
 * 
 */
@Entity
@Table(name="BLT_BRD_ACT")
@NamedQuery(name="BltBrdAct.findAll", query="SELECT b FROM BltBrdAct b")
@SequenceGenerator(name = "BLT_BRD_ACT_SEQUENCE", sequenceName = "BLT_BRD_ACT_SEQ", allocationSize = 1)
public class BltBrdAct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_ACT_SEQUENCE")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ACT_TYPE", referencedColumnName="ID", nullable=false)
	private RefCode actType;
	
	@ManyToOne
	@JoinColumn(name="User",  referencedColumnName="ID", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="POST_ID", referencedColumnName="ID", nullable=false)
	private BltBrdPost post;

	@Column(name="TIME_STAMP")
	private Timestamp timeStamp;

	public BltBrdAct() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RefCode getActType() {
		return actType;
	}

	public BltBrdAct(long id, RefCode actType, User user, BltBrdPost post, Timestamp timeStamp) {
		super();
		this.id = id;
		this.actType = actType;
		this.user = user;
		this.post = post;
		this.timeStamp = timeStamp;
	}

	public void setActType(RefCode actType) {
		this.actType = actType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BltBrdPost getPost() {
		return post;
	}

	public void setPost(BltBrdPost post) {
		this.post = post;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}



}