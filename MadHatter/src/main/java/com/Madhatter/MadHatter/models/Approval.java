package com.Madhatter.MadHatter.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "approval")
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String approvingAuthority;

    @Column
    private String decision;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference
    private Form form;

    public Approval() {
    }

    public Approval(String name, String approvingAuthority, String decision) {
        this.name = name;
        this.approvingAuthority = approvingAuthority;
        this.decision = decision;
    }

    public Approval(String name, String approvingAuthority, String decision, Form form) {
        this.name = name;
        this.approvingAuthority = approvingAuthority;
        this.decision = decision;
        this.form = form;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the approvingAuthority
     */
    public String getApprovingAuthority() {
        return approvingAuthority;
    }

    /**
     * @param approvingAuthority the approvingAuthority to set
     */
    public void setApprovingAuthority(String approvingAuthority) {
        this.approvingAuthority = approvingAuthority;
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the form
     */
    public Form getForm() {
        return form;
    }

    /**
     * @param form the form to set
     */
    public void setForm(Form form) {
        this.form = form;
    }
}