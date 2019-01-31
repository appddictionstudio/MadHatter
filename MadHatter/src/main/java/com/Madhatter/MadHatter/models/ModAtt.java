package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="MOD_ATT")

public class ModAtt implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOD_ATT_SEQUENCE")
	private long id;
	
	@Column(name="ATTACHMENT_ID")
	private long attachmentId;
	
	@ManyToOne
	@JoinColumn(name="MOD_ID", referencedColumnName="ID")
	@JsonIgnore
	private Modules mod;
	 
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


	public Modules getMod() {
		return mod;
	}

	public void setMod(Modules mod) {
		this.mod = mod;
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