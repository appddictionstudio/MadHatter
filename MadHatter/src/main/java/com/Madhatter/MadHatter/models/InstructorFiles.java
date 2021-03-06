package com.Madhatter.MadHatter.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "file")
public class InstructorFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Column
    private String fileType;
    
    @Column
    private String fileDesc;
    
    @Column
    private Number topic;

    @Column
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "form_id")
    @JsonBackReference
    private Form form;

    public InstructorFiles() {}

    public InstructorFiles(String fileName, String fileType, String fileDesc, Number topic, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDesc = fileDesc;
        this.topic = topic;
        this.data = data;
    }

    public InstructorFiles(String fileName, String fileType, String fileDesc, Number topic, byte[] data, Form form) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDesc = fileDesc;
        this.topic = topic;
        this.data = data;
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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    /**
     * @return the fileType
     */
    public String getFileDesc() {
        return fileDesc;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileDesc(String fileDesc) {
    	this.fileDesc = fileDesc;
    }
    
    public Number getTopic() {
        return topic;
    }

    /**
     * @param data the data to set
     */
    public void setTopic(Number topic) {
        this.topic = topic;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
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