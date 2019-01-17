package com.Madhatter.MadHatter.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.ResourcesRepository;
import com.Madhatter.MadHatter.models.Modules;



@RestController
@RequestMapping("/resources")
public class ResourcesController {
	@Autowired
    private ResourcesRepository repo;

  	@RequestMapping(value = "/getResourceByModId/{id}", method = RequestMethod.GET)
  	ResponseEntity<Optional<Modules>> getResourceByModId(@PathVariable long id) {
  		System.out.println("This is starting");
		Optional<Modules> resources = repo.findByResourceId(id);
		System.out.println("We are about to display");
		return ResponseEntity.ok(resources);
  	}

}
