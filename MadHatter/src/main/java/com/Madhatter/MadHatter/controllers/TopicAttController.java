package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.TopicAttRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.TopicAtt;
import com.Madhatter.MadHatter.services.AttachmentService;






@RestController
public class TopicAttController {
	@Autowired
    private TopicAttRepository repo;
	
	@Autowired
	private AttachmentService attService;
	
	
	//----Create
	@PostMapping(value = "/TopicAtt")
	@Transactional
	public ResponseEntity<Object> createBulletinAtt(@RequestBody TopicAtt topicAtt) {

		if (repo.findById(topicAtt.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}

		TopicAtt savedBltBrdAtt = repo.save(topicAtt);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBltBrdAtt.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	//----Upload--------
	
	@PostMapping(value="/uploadDoc")
    public ResponseEntity<TopicAtt> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
       	 
       	 Attachment att = attService.storeFile(file);
       	 
       	 TopicAtt topicAtt = new TopicAtt();

       	 topicAtt.setAttachment(att);
       	topicAtt.setFileNm(file.getOriginalFilename());
       	topicAtt.setFileSz(new Long(att.getAttachment().length));
           
            
            repo.save(topicAtt);

            return ResponseEntity.ok(topicAtt);
        } catch (Exception e) {
            throw e;
        }
    }
	
	// --------------- Load---------------------------------------
		// -----------------------------------------------------------
		@RequestMapping(value = "/TopicAtt", method = RequestMethod.GET)
		ResponseEntity<List<TopicAtt>> getAllBulletinAtt() {
			List<TopicAtt> attList = repo.getAllTopicAtt();
			return ResponseEntity.ok(attList);
		};
	
	//------------Upload Attachment By Topic Id-----
	
			@RequestMapping(value = "/topicatt/{id}", method = RequestMethod.GET)
			ResponseEntity<ByteArrayResource> getAttByTopicAttId(@PathVariable long id) {
				Optional<Attachment> attachment = repo.getAttachmentByTopicAttId(id);
				
				String ext = attachment.get().getMimeType().getExtension();
				String mimeType = attachment.get().getMimeType().getMimeType();
				
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(mimeType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attachment.\"" + ext + "\"")
						.body(new ByteArrayResource(attachment.get().getAttachment()));
			}
			
	
}
