
package com.Madhatter.MadHatter.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "video")

public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String urllink;

    public Video() {}

    public Video(String name, String urllink) {
        this.name = name;
        this.urllink = urllink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrllink() {
        return urllink;
    }

    public void setUrllink(String urllink) {
        this.urllink = urllink;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}