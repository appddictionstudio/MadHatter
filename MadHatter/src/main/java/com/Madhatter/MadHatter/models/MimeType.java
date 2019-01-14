package com.Madhatter.MadHatter.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MIME_TYPES")
@NamedQuery(name="MimeType.findAll", query="SELECT m FROM MimeType m")
@SequenceGenerator(name = "MIME_TYPES_SEQUENCE", sequenceName = "MIME_TYPES_SEQ", allocationSize = 1)
public class MimeType implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7786562750931361564L;
	@Column(length = 10)
    private String extension;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MIME_TYPES_SEQUENCE")
    @Column(nullable = false)
    private long id;
    @Column(name="MIME_TYPE", length = 100)
    private String mimeType;
	public MimeType() {
		
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	

    
    
    
    
	

}
