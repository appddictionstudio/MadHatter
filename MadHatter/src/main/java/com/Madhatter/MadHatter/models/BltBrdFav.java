package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the BLT_BRD_FAV database table.
 * 
 */
@Entity
@Table(name="BLT_BRD_FAV")
@SequenceGenerator(name = "BLT_BRD_FAV_SEQUENCE", sequenceName = "BLT_BRD_FAV_SEQ", allocationSize = 1)
public class BltBrdFav implements Serializable {
	private static final long serialVersionUID = 1L;


	
	public BltBrdFav(long id, Timestamp created, User user, BltBrdPost post) {
		super();
		this.id = id;
		this.created = created;
		this.user = user;
		this.post = post;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_FAV_SEQUENCE")
	private long id;


	@Column(name="CREATED")
	private Timestamp created;
	
	@ManyToOne
	@JoinColumn(name="USER", referencedColumnName="ID", nullable=false)
	private User user;

	//bi-directional many-to-one association to BltBrdPost
	@ManyToOne
	@JoinColumn(name="POST_ID", referencedColumnName="ID", nullable=false)
	private BltBrdPost post;

	public BltBrdFav() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
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




}