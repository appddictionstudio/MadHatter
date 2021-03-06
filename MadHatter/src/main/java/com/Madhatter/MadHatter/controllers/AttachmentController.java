package com.Madhatter.MadHatter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.AttachmentRepository;
import com.Madhatter.MadHatter.models.Attachment;

@RestController
@RequestMapping("/att")
public class AttachmentController {

	
	@Autowired
	private AttachmentRepository repo; 
//--------------- Load -----------------------------------------------------------
	 @RequestMapping(method = RequestMethod.GET)
	 ResponseEntity<List<Attachment>> getAllAtt() {
		 List<Attachment> postList = repo.getAllAttachments();
		 return ResponseEntity.ok(postList);
	 }
	 
}
	 