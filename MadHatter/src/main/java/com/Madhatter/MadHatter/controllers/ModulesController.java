package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.ModulesRepository;
import com.Madhatter.MadHatter.models.Modules;

@RestController
@CrossOrigin
@RequestMapping(value="/api/modules")
public class ModulesController {
	
	@Autowired
	private ModulesRepository repo;
	// ------------------- Create
//	
//    @PostMapping("/ModuleSet")
//    @Transactional
//    public ResponseEntity<Object> createBootcampModules(@RequestBody Modules module) {
//    	System.out.println("Something here");
//    	if (repo.findById(module.getId()).isPresent()) {
//			throw new ValidationException("Record Already Exists");
//		}
//    	
//    	Modules savedModule = repo.save(module);
//    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedModule.getId()).toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	//--------------- Load -----------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Modules>> getAllModules() {    
       List<Modules> moduleList = repo.getAllModules();    
       return ResponseEntity.ok(moduleList);
       }
}