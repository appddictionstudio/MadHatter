package com.Madhatter.MadHatter.payloads;

import java.util.List;

import com.Madhatter.MadHatter.models.Approval;

import org.springframework.web.multipart.MultipartFile;

public class FormRequest {

    private String formNumber;
    private String formName;
    private String formSubmissionTimeStamp;
    private List<Data> data;
    private List<MultipartFile> files;
    private List<Approval> approvals;

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
     * @return the data
     */
    public List<Data> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<Data> data) {
        this.data = data;
    }

    /**
     * @return the files
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
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
}