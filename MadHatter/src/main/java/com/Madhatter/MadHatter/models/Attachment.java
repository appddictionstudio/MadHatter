package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.*;


@Entity
@Table(name="ATTACHMENTS")
@NamedQuery(name="Attachment.findAll", query="SELECT a FROM Attachment a")
@SequenceGenerator(name = "ATTACHMENT_SEQUENCE", sequenceName = "ATTACHMENTS_SEQ", allocationSize = 1)
public class Attachment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_SEQUENCE")
    private long id;
    
	@Lob
    @Column
    private byte[] attachment;
    
	@Column
    private String title; 
    
	@ManyToOne
    @JoinColumn(name = "MIME_TYPE_ID")
    private MimeType mimeType;

	public Attachment() {
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MimeType getMimeType() {
		return mimeType;
	}

	public void setMimeType(MimeType mimeType) {
		this.mimeType = mimeType;
	}
	
	
    
 	
	

	
}
