package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.TopicRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.Modules;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;




@RestController
@RequestMapping("/topics")
public class TopicController {
	@Autowired
    private TopicRepository repo;


	  //--------------- Load -----------------------------------------------------------
	  	 @RequestMapping(value="/all",method = RequestMethod.GET)
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

		//--------------- set hidden or shown -----------------------------------------------------------
//		@RequestMapping(value = "/hide/{modId}", method = RequestMethod.POST)
//	  	public ResponseEntity<Topic> setHidden(@RequestBody Topic topic	, @PathVariable long modId) {
//
//			Modules mod = new Modules();
//			mod.setId(modId);
//
//			topic.setHidden("true");
//	  		Topic savedHiddenTopic = repo.save(topic);
////	  		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
////	  		        .buildAndExpand(savedHiddenTopic.getId()).toUri();
//	  		return ResponseEntity.ok(savedHiddenTopic);
//
//	  	    }
		
		
		//---------------Create Topic -------------------------------
		//-----------------------------------------------------------
		
		@RequestMapping(value = "/topic")
		@PostMapping()
		@Transactional
		public ResponseEntity<Object> createTopic(@RequestBody Topic topic){
			
			if(repo.findById(topic.getId()).isPresent()) {
				throw new ValidationException("Record Already Exists");
			}
			
			Modules mod = new Modules();
			mod.setId((long) 1);
			
			topic.setMod(mod);
			
			if(topic.getAttachments() != null) {
				for(TopicAtt attachment: topic.getAttachments()) {
					attachment.setTopic(topic);
				}
			}
			
			Topic savedTopic = repo.save(topic);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedTopic.getId()).toUri();
			return ResponseEntity.created(location).build();
			
		}
		
		
		//---------------Update Topic---------------------------------
		//------------------------------------------------------------
		
		@RequestMapping(value = "/{id}", method = RequestMethod.POST)
		public ResponseEntity<Object> updateTopic(@RequestBody Topic topic, @PathVariable long id){
			if(topic.getAttachments() != null) {
				for(TopicAtt attachment: topic.getAttachments()) {
					attachment.setTopic(topic);
				}
			}
			topic.setId(id);
//       	TopicAtt topicAttachment = new TopicAtt();
//       	topicAttachment.setTopic(topic);
			repo.save(topic);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		

}
		
