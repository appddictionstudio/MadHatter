package com.Madhatter.MadHatter.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;

    @Column
    private String fileType;
    
    @Column
    private String fileDesc;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @JsonBackReference
    private Topic topic;
    
    

    public File() {}

    public File(String fileName, String fileType, String fileDesc) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDesc = fileDesc;
    }

    public File(String fileName, String fileType, String fileDesc,  Topic topic) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDesc = fileDesc;
        this.topic = topic;
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


    /**
     * @return the form
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * @param form the form to set
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}