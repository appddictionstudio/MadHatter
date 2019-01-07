
package com.Madhatter.MadHatter.models;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "certification")

public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cert_name;

    public Certification() {}

    public Certification(String cert_name) {
        this.cert_name = cert_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCertName() {
        return cert_name;
    }

    public void setCertName(String cert_name) {
        this.cert_name = cert_name;
    }
}