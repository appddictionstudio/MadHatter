package com.Madhatter.MadHatter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.ModulesRepository;
import com.Madhatter.MadHatter.models.Modules;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;


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
    
 // --------------- Load Post By Id
 		// -----------------------------------------------------------
 	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
 		ResponseEntity<Optional<Modules>> getModById(@PathVariable long id) {
		Optional<Modules> post = repo.findById(id);
		return ResponseEntity.ok(post);
 	}
 	
 	//-------------------------------------put by id------------------------------------------------\\
 	
// 	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Object> updateTopic(@RequestBody Topic topic, @PathVariable long id){
//		if(topic.getAttachments() != null) {
//			for(TopicAtt attachment: topic.getAttachments()) {
//				attachment.setTopic(topic);
//			}
//		}
//		topic.setId(id);
////   	TopicAtt topicAttachment = new TopicAtt();
////   	topicAttachment.setTopic(topic);
//		repo.save(topic);
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
}