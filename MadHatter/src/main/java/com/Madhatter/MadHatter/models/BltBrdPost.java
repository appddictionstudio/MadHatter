package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the BLT_BRD_POST database table.
 * 
 */
@Entity
@Table(name="BLT_BRD_POST")
@SequenceGenerator(name = "BLT_BRD_POST_SEQUENCE", sequenceName = "BLT_BRD_POST_SEQ", allocationSize = 1)
public class BltBrdPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_POST_SEQUENCE")
	private long id;

	@ManyToOne
	@JoinColumn(name="AUTHOR_ID", referencedColumnName="ID", nullable=false)
	private User author;

	@Column(name="HOT_TOPIC_YN")
	private String hotTopicYn;

	@Column(name="POST_DATE")
	private Timestamp postDate;

	@Lob
	private String text;
	

	@Column(name="TOPIC")
	private String topic;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID", nullable=false)
	private RefCode category;

	@Column(name="LOCK_YN")
	private String lockYn;
	
	@Column(name="LIKE_COUNT")
	private Integer likeCount;
	

	//bi-directional many-to-one association to BltBrdFav
	@OneToMany(cascade=CascadeType.ALL, mappedBy="post")
	@JsonIgnore
	private List<BltBrdFav> bltBrdFavs;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="post")
	@JsonIgnore
	private List<BltBrdAct> bltBrdActs;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	@JoinColumn(name = "post_id")
	private List<BltBrdPostComment> bltPostComment;
	

	public String getLockYn() {
		return lockYn;
	}

	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}

	public List<BltBrdPostComment> getBltPostComment() {
		return bltPostComment;
	}

	public void setBltPostComment(List<BltBrdPostComment> bltPostComment) {
		this.bltPostComment = bltPostComment;
	}

	public BltBrdPost() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getHotTopicYn() {
		return this.hotTopicYn;
	}

	public void setHotTopicYn(String hotTopicYn) {
		this.hotTopicYn = hotTopicYn;
	}

	public Timestamp getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public RefCode getCategory() {
		return category;
	}

	public void setCategory(RefCode category) {
		this.category = category;
	}

	public List<BltBrdFav> getBltBrdFavs() {
		return this.bltBrdFavs;
	}

	public void setBltBrdFavs(List<BltBrdFav> bltBrdFavs) {
		this.bltBrdFavs = bltBrdFavs;
	}

	public List<BltBrdAct> getBltBrdActs() {
		return bltBrdActs;
	}

	public void setBltBrdActs(List<BltBrdAct> bltBrdActs) {
		this.bltBrdActs = bltBrdActs;
	}

	public BltBrdFav addBltBrdFav(BltBrdFav bltBrdFav) {
		getBltBrdFavs().add(bltBrdFav);
		bltBrdFav.setPost(this);

		return bltBrdFav;
	}

	public BltBrdFav removeBltBrdFav(BltBrdFav bltBrdFav) {
		getBltBrdFavs().remove(bltBrdFav);
		bltBrdFav.setPost(null);

		return bltBrdFav;
	}


}