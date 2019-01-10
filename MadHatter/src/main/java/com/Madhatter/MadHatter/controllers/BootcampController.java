package com.Madhatter.MadHatter.controllers;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.BootcampRepository;
import com.Madhatter.MadHatter.models.Bootcamp;

@RestController
@CrossOrigin
@RequestMapping(value="/api/bootcamp")
public class BootcampController {
	
	@Autowired
    private BootcampRepository repo;
		
    @PostMapping("/bootcampSet")
    public ResponseEntity<Object> getBootcampModules(@RequestBody Bootcamp bootcamp) {
    	System.out.println("Something here");
    	if (repo.findById(bootcamp.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}
		
//		if(bootcamp.getAttachments() != null) {
//			for(bootcamp attachment: bootcamp.getAttachments()) {
//				attachment.setPost(bootcamp);
//			}
    	
    	Bootcamp savedBootcamp = repo.save(bootcamp);
		return null;
	}
}