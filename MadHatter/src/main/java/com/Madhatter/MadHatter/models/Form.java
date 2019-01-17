package com.Madhatter.MadHatter.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String formNumber;

    @Column
    private String formName;

    @Column
    private String formSubmissionTimeStamp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form")
    @JsonManagedReference
    private List<Approval> approvals;


    @ElementCollection
    @MapKeyColumn(name = "form_question")
    @Column(name = "form_answer")
    @CollectionTable(name = "form_data", joinColumns = @JoinColumn(name = "form_id"))
    Map<String, String> data = new HashMap<String, String>();

    public Form() {
    }

    public Form(String formNumber, String formName, String formSubmissionTimeStamp) {
        this.formNumber = formNumber;
        this.formName = formName;
        this.formSubmissionTimeStamp = formSubmissionTimeStamp;
    }

    public Form(String formNumber, String formName, String formSubmissionTimeStamp, HashMap<String, String> data, List<Approval> approvals) {
        this.formNumber = formNumber;
        this.formName = formName;
        this.formSubmissionTimeStamp = formSubmissionTimeStamp;
        this.data = data;
        this.approvals = approvals;
    }

    public Form(HashMap<String, String> data) {
        this.data = data;
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
     * @return the formNumber
     */
    public String getFormNumber() {
        return formNumber;
    }

    /**
     * @param formNumber the formNumber to set
     */
    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    /**
     * @return the formName
     */
    public String getFormName() {
        return formName;
    }

    /**
     * @param formName the formName to set
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * @return the formSubmissionTimeStamp
     */
    public String getFormSubmissionTimeStamp() {
        return formSubmissionTimeStamp;
    }

    /**
     * @param formSubmissionTimeStamp the formSubmissionTimeStamp to set
     */
    public void setFormSubmissionTimeStamp(String formSubmissionTimeStamp) {
        this.formSubmissionTimeStamp = formSubmissionTimeStamp;
    }

    /**
     * @return the approvals
     */
    public List<Approval> getApprovals() {
        return approvals;
    }

    /**
     * @param approvals the approvals to set
     */
    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
    }


    /**
     * @return the data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, String> data) {
        this.data = data;
    }
}