package com.Madhatter.MadHatter.models;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//This is where I stopped https://dzone.com/articles/spring-data-jpa-auditing-automatically-the-good-stuff

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(createdBy, creationDate, lastModifiedBy, lastModifiedDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auditable other = (Auditable) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(lastModifiedBy, other.lastModifiedBy)
				&& Objects.equals(lastModifiedDate, other.lastModifiedDate);
	}

	@CreatedBy
	@Column(name="CREATED_BY")
    protected U createdBy;
    @CreatedDate
    @Column(name="CREATION_DATE")
    @Temporal(TIMESTAMP)
    protected Date creationDate;
    @LastModifiedBy
    @Column(name="LAST_MODIFIED_BY")
    protected U lastModifiedBy;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name="LAST_MODIFIED_DATE")
    protected Date lastModifiedDate;
    
    public U getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public U getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public String toString() {
		return "Auditable [createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastModifiedBy="
				+ lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}
