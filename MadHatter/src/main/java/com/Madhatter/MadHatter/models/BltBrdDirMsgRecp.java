package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the BLT_BRD_DIR_MSG_RECP database table.
 * 
 */
@Entity
@Table(name="BLT_BRD_DIR_MSG_RECP")
@NamedQuery(name="BltBrdDirMsgRecp.findAll", query="SELECT b FROM BltBrdDirMsgRecp b")
@SequenceGenerator(name = "BLT_BRD_DIR_MSG_RECP_SEQUENCE", sequenceName = "BLT_BRD_DIR_MSG_RECP_SEQ", allocationSize = 1)
public class BltBrdDirMsgRecp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_DIR_MSG_RECP_SEQUENCE")
	private Long id;

	@ManyToOne
	@JoinColumn(name="RECIPIENT_ID")
	private User user;

	//bi-directional many-to-one association to BltBrdDirMsg
	@ManyToOne
	@JoinColumn(name="MESSAGE_ID", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private BltBrdDirMsg message;

	public BltBrdDirMsgRecp() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getRecipient() {
		return user;
	}

	public void setRecipient(User user) {
		this.user = user;
	}

	public BltBrdDirMsg getMessage() {
		return message;
	}

	public void setMessage(BltBrdDirMsg message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}