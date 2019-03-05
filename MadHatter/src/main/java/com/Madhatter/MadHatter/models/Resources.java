
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resources")

public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
    private String links;

    @ManyToOne
	@JoinColumn(name="mod_to_resource_id", referencedColumnName="ID", nullable=false)
	@JsonIgnore
	private Modules mod;
    
    public Resources() {}

    public Resources(String links, Modules mod) {
    	this.links = links;
    	this.mod = mod;
    }
	
    public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}
	
	public Modules getMod() {
			return mod;
		}

	public void setMod(Modules mod) {
			this.mod = mod;
		}
}