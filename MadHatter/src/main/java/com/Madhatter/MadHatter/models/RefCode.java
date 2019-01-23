package com.Madhatter.MadHatter.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the REF_CODES database table.
 * extends  Auditable<String>
 */
@Entity
@Table(name="REF_CODES")
@NamedQuery(name="RefCode.findAll", query="SELECT r FROM RefCode r")
public class RefCode extends  Auditable<String> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REF_CODES_ID_GENERATOR", sequenceName="REF_CODES_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REF_CODES_ID_GENERATOR")
	private long id;

	@Column(name="CIDNE_NM")
	private String cidneNm;

	@Column(name="CUSTOM_CATEGORY")
	private String customCategory;

	@Column(name="DATA_TYPE")
	private String dataType;

	private String descr;

	@Column(name="MAX_SIZE")
	private String maxSize;

	private String nm;

	@Column(name="OBSOLETE_YN")
	private String obsoleteYn;

	@Column(name="PERMIT_ADD_CHILDREN_YN")
	private String permitAddChildrenYn;

	@Column(name="REG_EXP")
	private String regExp;

	@Column(name="SORT_ORDER")
	private BigDecimal sortOrder;

	//uni-directional many-to-one association to RefCode
	@ManyToOne(optional=true)	
	@JoinColumn(name="PARENT_ID", insertable=false, updatable=false)
	private RefCode parentRefCode;
	
	@Column(name="PARENT_ID", nullable=true)
	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public RefCode() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCidneNm() {
		return this.cidneNm;
	}

	public void setCidneNm(String cidneNm) {
		this.cidneNm = cidneNm;
	}

	public String getCustomCategory() {
		return this.customCategory;
	}

	public void setCustomCategory(String customCategory) {
		this.customCategory = customCategory;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getMaxSize() {
		return this.maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getObsoleteYn() {
		return this.obsoleteYn;
	}

	public void setObsoleteYn(String obsoleteYn) {
		this.obsoleteYn = obsoleteYn;
	}

	public String getPermitAddChildrenYn() {
		return this.permitAddChildrenYn;
	}

	public void setPermitAddChildrenYn(String permitAddChildrenYn) {
		this.permitAddChildrenYn = permitAddChildrenYn;
	}

	public String getRegExp() {
		return this.regExp;
	}

	public void setRegExp(String regExp) {
		this.regExp = regExp;
	}

	public BigDecimal getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(BigDecimal sortOrder) {
		this.sortOrder = sortOrder;
	}

	public RefCode getParentRefCode() {
		return this.parentRefCode;
	}

	public void setParentRefCode(RefCode parentRefCode) {
		this.parentRefCode = parentRefCode;
	}

}