package com.Madhatter.MadHatter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.ResourcesRepository;
import com.Madhatter.MadHatter.models.Modules;
import com.Madhatter.MadHatter.models.Resources;
import com.Madhatter.MadHatter.models.Topic;



@RestController
@RequestMapping("/resources")
public class ResourcesController {
	@Autowired
    private ResourcesRepository repo;

	@RequestMapping(value = "/getResourceByModId/{id}", method = RequestMethod.GET)
  	ResponseEntity<Optional<Modules>> getResourceByModId(@PathVariable long id) {
		Optional<Modules> resources = repo.findByResourceId(id);
		return ResponseEntity.ok(resources);
  	}
	
	@RequestMapping(value = "/getAllResource", method = RequestMethod.GET)
  	ResponseEntity<List<Modules>> getAll() {
		List<Modules> resources = repo.findByAll();
		return ResponseEntity.ok(resources);
  	}
	
	@RequestMapping(value = "/removeResource/{id}", method = RequestMethod.DELETE)
  	ResponseEntity<String> deleteById(@PathVariable long id) {
		repo.deleteById(id);
		return ResponseEntity.ok("");
  	}
  	
  	@RequestMapping(value = "/postResourceByModId/{id}", method = RequestMethod.POST)
  	public ResponseEntity<Object> postResourceByModId(@PathVariable long id, @RequestBody String link) {
  		Resources resources = new Resources();
  		Modules mod = new Modules();
		mod.setId(id);
		resources.setLinks(link);
		resources.setMod(mod);
		
		repo.save(resources);
		return ResponseEntity.status(HttpStatus.OK).build();
		
  	}

}
