package com.Madhatter.MadHatter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.AttachmentRepository;
import com.Madhatter.MadHatter.Repositories.SubmittedAttachmentRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.SubmittedAttachment;

@RestController
@RequestMapping("/Satt")
public class SubmittedAttachmentController {

	
	@Autowired
	private SubmittedAttachmentRepository repo; 
//--------------- Load -----------------------------------------------------------
	 @RequestMapping(method = RequestMethod.GET)
	 ResponseEntity<List<SubmittedAttachment>> getAllAtt() {
		 List<SubmittedAttachment> postList = repo.getAllAttachments();
		 return ResponseEntity.ok(postList);
	 }
	 
}
	 