package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the BLT_BRD_DIR_MSG database table.
 * 
 */
@Entity
@Table(name="BLT_BRD_DIR_MSG")
@NamedQuery(name="BltBrdDirMsg.findAll", query="SELECT b FROM BltBrdDirMsg b")
@SequenceGenerator(name = "BLT_BRD_DIR_MSG_SEQUENCE", sequenceName = "BLT_BRD_DIR_MSG_SEQ", allocationSize = 1)
public class BltBrdDirMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	public BltBrdDirMsg(String message, User sender, List<BltBrdDirMsgRecp> bltBrdDirMsgRecps) {
		super();
		this.message = message;
		this.sender = sender;
		this.bltBrdDirMsgRecps = bltBrdDirMsgRecps;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLT_BRD_DIR_MSG_SEQUENCE")
	private Long id;

	@Lob
	@Column(name="MESSAGE")
	private String message;

	@ManyToOne
	@JoinColumn(name="SENDER_ID")
	private User sender;

	//bi-directional many-to-one association to BltBrdDirMsgRecp
	@OneToMany(mappedBy="message",cascade=CascadeType.ALL, orphanRemoval = true)
	private List<BltBrdDirMsgRecp> bltBrdDirMsgRecps;

	public BltBrdDirMsg() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public  User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public List<BltBrdDirMsgRecp> getBltBrdDirMsgRecps() {
		return this.bltBrdDirMsgRecps;
	}

	public void setBltBrdDirMsgRecps(List<BltBrdDirMsgRecp> bltBrdDirMsgRecps) {
		this.bltBrdDirMsgRecps = bltBrdDirMsgRecps;
	}

	public BltBrdDirMsgRecp addBltBrdDirMsgRecp(BltBrdDirMsgRecp bltBrdDirMsgRecp) {
		getBltBrdDirMsgRecps().add(bltBrdDirMsgRecp);
		bltBrdDirMsgRecp.setMessage(this);

		return bltBrdDirMsgRecp;
	}

	public BltBrdDirMsgRecp removeBltBrdDirMsgRecp(BltBrdDirMsgRecp bltBrdDirMsgRecp) {
		getBltBrdDirMsgRecps().remove(bltBrdDirMsgRecp);
		bltBrdDirMsgRecp.setMessage(null);

		return bltBrdDirMsgRecp;
	}

}