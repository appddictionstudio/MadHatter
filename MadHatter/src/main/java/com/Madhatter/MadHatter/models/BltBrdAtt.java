package com.Madhatter.MadHatter.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="BLT_BRD_ATT")
@SequenceGenerator(name = "BLT_BRD_ATT_SEQUENCE", sequenceName = "BLT_BRD_ATT_SEQ", allocationSize = 1)
public class BltBrdAtt implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444945510014842409L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_ATT_SEQUENCE")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="POST_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private BltBrdPost post;
	
	@ManyToOne
	@JoinColumn(name="ATTACHMENT_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private Attachment attachment;
	
	@ManyToOne
	@JoinColumn(name="THUMBNAIL_ATTACHMENT_ID", referencedColumnName="ID")
	private Attachment thumbnail;
	
    @ManyToOne(optional = true)
    @JoinColumn(name = "DOC_TYPE_ID", referencedColumnName = "ID", nullable=false)
    private RefCode docType;
	    
	@Column(name="FILE_NM")
	private String fileNm;
	
	@Column(name="FILE_SZ")
	private long fileSz;
	
	@Column(name="DESCRIPTION")
	private String description;

	public BltBrdAtt() {
		
	}

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

	public Attachment getAttachment() {
		return attachment;
	}
	

	public Attachment getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Attachment thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public RefCode getDocType() {
		return docType;
	}

	public void setDocType(RefCode docType) {
		this.docType = docType;
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
