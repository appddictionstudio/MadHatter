package com.Madhatter.MadHatter.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Madhatter.MadHatter.Repositories.FormRepository;
import com.Madhatter.MadHatter.models.Approval;
import com.Madhatter.MadHatter.models.Form;
import com.Madhatter.MadHatter.payloads.APIResponse;
import com.Madhatter.MadHatter.payloads.AppException;
import com.Madhatter.MadHatter.payloads.ApprovalRequest;
import com.Madhatter.MadHatter.payloads.Data;
import com.Madhatter.MadHatter.payloads.FormRequest;
import com.Madhatter.MadHatter.services.FileStorageService;

@RestController
@RequestMapping("/api/form")
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/{formNumber}")
    private Form getFormByFormId(@PathVariable String formNumber) {
        return formRepository.findByFormNumber(formNumber)
                .orElseThrow(() -> new AppException("No form found by that form number!"));
    }

    @GetMapping("/getNextFormNumberInGroup-{formNumGroup}")
    private ResponseEntity<?> getNextFormNumber(@PathVariable String formNumGroup) {
        String lastFormNumber = formRepository.findLastFormNumber(formNumGroup);

        if (lastFormNumber == null) {
            String newFormNumber = formNumGroup + "-00001";
            return ResponseEntity.ok().body(new APIResponse(true, newFormNumber));
        } else {
            String newFormNumberWithZeros = addZerosToFormNumber(
                    Long.toString(Long.parseLong(lastFormNumber.split("-")[1]) + 1));
            String newFormNumber = formNumGroup + "-" + newFormNumberWithZeros;
            return ResponseEntity.ok().body(new APIResponse(true, newFormNumber));
        }
    }

    @GetMapping("/formsAwaitingApproval")
    private List<Form> getFormsAwaitingApproval() {
        List<Form> allForms = formRepository.findAll();
        List<Form> formsAwaitingApproval = new ArrayList<>();

        for (Form form : allForms) {
            if (form.getApprovals().size() <= 3) {
                formsAwaitingApproval.add(form);
            }
        }

        return formsAwaitingApproval;
    }

    @PostMapping("/submit-form")
    public ResponseEntity<?> submitNewForm(@RequestBody FormRequest formRequest) throws Exception {
        Form form = new Form(formRequest.getFormNumber(), formRequest.getFormName(),
                formRequest.getFormSubmissionTimeStamp());

        if (formRequest.getFiles() != null) {
            for (MultipartFile file : formRequest.getFiles()) {
                fileStorageService.storeFile(file, form);
            }
        }

        for (Data data : formRequest.getData()) {
            form.getData().put(data.getFormQuestion(), data.getFormAnswer());
        }

        formRepository.save(form);

        return ResponseEntity.ok().body(new APIResponse(true, "Form has been submitted successfully"));
    }

    @PostMapping("/approve-form-{formNumber}")
    private ResponseEntity<?> approveFormByFormNumber(@PathVariable String formNumber,
            @RequestBody ApprovalRequest approvalRequest) {
        Form form = formRepository.findByFormNumber(formNumber)
                .orElseThrow(() -> new AppException("No form found with that Form Number"));

        form.getApprovals().add(new Approval(approvalRequest.getName(), approvalRequest.getApprovingAuthority(),
                approvalRequest.getDecision(), form));
        formRepository.save(form);

        return ResponseEntity.ok().body(new APIResponse(true, "Form successfully approved!"));
    }

    public String addZerosToFormNumber(String formNum) {
        Integer lenOfFormNumber = formNum.length();

        switch (lenOfFormNumber) {
        case 1:
            formNum = "0000" + formNum;
            break;

        case 2:
            formNum = "000" + formNum;
            break;

        case 3:
            formNum = "00" + formNum;
            break;

        case 4:
            formNum = "0" + formNum;
            break;

        case 5:
            break;
        }

        return formNum;
    }
}