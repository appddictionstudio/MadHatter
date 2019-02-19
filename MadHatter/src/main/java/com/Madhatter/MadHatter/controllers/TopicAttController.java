package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.TopicAttRepository;
import com.Madhatter.MadHatter.Repositories.TopicRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.SubmittedAtt;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;
import com.Madhatter.MadHatter.services.AttachmentService;









@RestController
public class TopicAttController {
	@Autowired
    private TopicAttRepository repo;
	
	@Autowired
    private TopicRepository tRepo;
	
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

       	topicAtt.setAttachmentId(att.getId());
       	topicAtt.setFileNm(file.getOriginalFilename());
       	topicAtt.setFileSz(new Long(att.getAttachment().length));
       	
//       	Topic topic = new Topic();
//       	topic.setId(id);
//       	topicAtt.setTopic(topic);
//       	
       	
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
	
	//------------Load TopicAttachment By Topic Id-----
	
			@RequestMapping(value = "/topicatt/{id}", method = RequestMethod.GET)
			ResponseEntity<List<TopicAtt>>getAttByTopicId(@PathVariable long id) {

				
				List<TopicAtt> attach = repo.findByTopicId(id);
				
				return ResponseEntity.ok(attach);
			}
//			
	
		//----------------Downloading----------
		
		@RequestMapping(value = "/TopicAtt/downloadDoc/{id}", method = RequestMethod.GET)
		@ResponseBody
		public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws Exception {
			
			TopicAtt attachment = repo.getOne(id);
			Attachment att = attService.getFile(attachment.getAttachmentId());
			response.setContentType("application/octet-stream");
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		    response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getFileNm());

		    
		    /** assume that below line gives you file content in byte array **/
		    byte[] binary = att.getAttachment();
		    
//		    int x = fis.available();
//		    byte byteArray[] = new byte[x];
//		    logger.info(" File size :"+byteArray.length);
//		    fis.read(byteArray);

		    response.getOutputStream().write(binary);
		    response.flushBuffer();
//		    fis.close();
		}
		
//-----Update 
		
		@RequestMapping(value = "/topicAttUpdate", method = RequestMethod.PUT)
		public ResponseEntity<Object> updateTopicAtt(@RequestBody TopicAtt topicAtt){
//			if(topicAtt.getSubAttachments() != null) {
//				for(SubmittedAtt attachment: topicAtt.getSubAttachments()) {
//					attachment.setTopicatt(topicAtt);
//				}
//			}
			
			topicAtt.setId(topicAtt.getId());
			SubmittedAtt subAtt = new SubmittedAtt();
			System.out.println(topicAtt.getTopic());
			subAtt.setTopicatt(topicAtt);

			repo.save(topicAtt);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		
		
//-------------Delete
		@RequestMapping(value="/TopicAtt/{id}", method = RequestMethod.DELETE)
		@Transactional
		public ResponseEntity<TopicAtt> deleteTopicAtt(@PathVariable Long id){
			repo.deleteById(id);
			return new ResponseEntity<TopicAtt>(HttpStatus.NO_CONTENT);
		}
		
}
