
package com.Madhatter.MadHatter.models;
import javax.persistence.*;

import java.sql.Clob;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modules")

public class Modules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    
    @Column
    private String description;

    @Column
    private String iconLink;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "modules_minfo",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "moduleinfo_id"))
    private Set<ModuleInfo> minfo = new HashSet<>();
    
    public Modules() {}

    public Modules(String title, String description, String iconLink) {
    	this.title = title;
    	this.description = description;
    	this.iconLink = iconLink;
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Set<ModuleInfo> getMinfo() {
		return minfo;
	}

	public void setMinfo(Set<ModuleInfo> minfo) {
		this.minfo = minfo;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

}