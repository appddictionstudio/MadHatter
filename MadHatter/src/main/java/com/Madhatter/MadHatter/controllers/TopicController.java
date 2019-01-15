package com.Madhatter.MadHatter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.TopicRepository;
import com.Madhatter.MadHatter.models.Modules;
import com.Madhatter.MadHatter.models.Topic;



@RestController
@RequestMapping("/topics")
public class TopicController {
	@Autowired
    private TopicRepository repo;
	
	   
	  //--------------- Load -----------------------------------------------------------
	  	 @RequestMapping(method = RequestMethod.GET)
	  	 ResponseEntity<List<Topic>> getAllMhTopics() {	
	  	    List<Topic> postList = repo.getAllTopics();	 
	  	    return ResponseEntity.ok(postList);
	  	    }
	
	  //-----------------LoadTopicByModId------------------------------------------------------
	  	 
	  	@RequestMapping(value = "/getByModId/{id}", method = RequestMethod.GET)
		ResponseEntity<Optional<Modules>> getTopicByModId(@PathVariable long id) {
	  		
			Optional<Modules> topics = repo.findByModId(id);
			
			return ResponseEntity.ok(topics);
		};

}