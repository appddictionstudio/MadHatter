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

import com.Madhatter.MadHatter.Repositories.SubmittedAttRepository;
import com.Madhatter.MadHatter.Repositories.TopicAttRepository;
import com.Madhatter.MadHatter.Repositories.UserRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.SubmittedAtt;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;
import com.Madhatter.MadHatter.models.User;
import com.Madhatter.MadHatter.services.AttachmentService;







@RestController
public class SubmittedAttController {
	@Autowired
    private SubmittedAttRepository repo;
	
	@Autowired
	private AttachmentService attService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private TopicAttRepository topicAttrepo;
	
	//----Create
	@PostMapping(value = "/SubmittedAtt")
	@Transactional
	public ResponseEntity<Object> createSubmittedAtt(@RequestBody SubmittedAtt submittedAtt) {

		if (repo.findById(submittedAtt.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}

		SubmittedAtt savedSubmitteddAtt = repo.save(submittedAtt);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedSubmitteddAtt.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//-----Update 
	
			@RequestMapping(value = "submittedAtt/{id}", method = RequestMethod.PUT)
			public ResponseEntity<Object> updateSubmittedAtt(@RequestBody SubmittedAtt submittedAtt, @PathVariable long id){
//				
				submittedAtt.setTopicatt(submittedAtt.getTopicatt());
				submittedAtt.setId(submittedAtt.getId());
				
				TopicAtt tAtt = new TopicAtt();
				tAtt.setId(id);
				
				submittedAtt.setTopicatt(tAtt);

				repo.save(submittedAtt);
				return ResponseEntity.status(HttpStatus.OK).build();
			}
	
	
//	//----Upload--------
	
	@PostMapping(value="/uploadSDoc")
    public ResponseEntity<SubmittedAtt> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
       	 
       	 Attachment att = attService.storeFile(file);
       	 
       	SubmittedAtt submittedAtt = new SubmittedAtt();

       	submittedAtt.setAttachmentId(att.getId());
       	submittedAtt.setFileNm(file.getOriginalFilename());
       	submittedAtt.setFileSz(new Long(att.getAttachment().length));
       	

            repo.save(submittedAtt);

            return ResponseEntity.ok(submittedAtt);
        } catch (Exception e) {
            throw e;
        }
    }
	
	// --------------- Load---------------------------------------
		// -----------------------------------------------------------
		@RequestMapping(value = "/AllSubmittedAtt", method = RequestMethod.GET)
		ResponseEntity<List<SubmittedAtt>> getAllBulletinAtt() {
			List<SubmittedAtt> attList = repo.getAllSubmittedAtt();
			return ResponseEntity.ok(attList);
		};
		
		
	// --------------- Load by student---------------------------------------
		// -----------------------------------------------------------
		@RequestMapping(value = "/studentSubmittedAtt/{id}", method = RequestMethod.GET)
		ResponseEntity<List<SubmittedAtt>> getStudentSubmitted(@PathVariable long id) {

			List<SubmittedAtt> studentlst = repo.findByStudentId(id);
			
			for(SubmittedAtt stud : studentlst) {
				Hibernate.initialize(stud.getTopicatt());
			}
			return ResponseEntity.ok(studentlst);
		};
	
		
	// -------------------------Get all by Id----------------------------------------------\\
	
		@RequestMapping(value = "/SubmittedAtt/{id}", method = RequestMethod.GET)
		ResponseEntity<List<SubmittedAtt>> getAttByTopicId(@PathVariable long id) {

			List<SubmittedAtt> attach = repo.findByAttId(id);
			
			
			return ResponseEntity.ok(attach);
		}		
		
	//------------Upload Attachment By Topic Id-----
	
//			@RequestMapping(value = "/topicatt/{id}", method = RequestMethod.GET)
//			ResponseEntity<ByteArrayResource> getAttByTopicAttId(@PathVariable long id) {
//				Optional<Attachment> attachment = repo.getAttachmentByTopicAttId(id);
//				
//				String ext = attachment.get().getMimeType().getExtension();
//				String mimeType = attachment.get().getMimeType().getMimeType();
//				
//				return ResponseEntity.ok()
//						.contentType(MediaType.parseMediaType(mimeType))
//						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attachment.\"" + ext + "\"")
//						.body(new ByteArrayResource(attachment.get().getAttachment()));
//			}
//			
	
		//----------------Downloading----------
		
		@RequestMapping(value = "/SubmittedAtt/downloadDoc/{id}", method = RequestMethod.GET)
		@ResponseBody
		public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws Exception {
			
			SubmittedAtt attachment = repo.getOne(id);
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
		
		@RequestMapping(value = "subAtt/{topicAttId}", method = RequestMethod.PUT)
		public ResponseEntity<Object> subAtt(@RequestBody SubmittedAtt subAtt, @PathVariable Long topicAttId){
	
			subAtt.setId(subAtt.getId());
			subAtt.setGradeD((long) 100);
			subAtt.setGradeN((long) 0);
//			User creator = userRepo.findById((long) subAtt.getStudent().getId());
			
//			subAtt.setStudent(creator);
			
			
			
			TopicAtt tAtt = new TopicAtt();
//			System.out.println(subAtt.getTopicatt().getId());
			tAtt.setId(topicAttId);
			
			subAtt.setTopicatt(tAtt);
			
		
			repo.save(subAtt);
//			Optional<TopicAtt> topicAtt = topicAttrepo.findById(topicAttId);
			
//			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/302")
//					.buildAndExpand(saveSubAtt.getId()).toUri();
//			return ResponseEntity.created(location).build();
			return ResponseEntity.status(HttpStatus.OK).build();


		}
		
		//----------------GET ATT BY STUDENT ID----------\\
		@RequestMapping(value = "checkStudentSubmittedAtt", method = RequestMethod.GET)
		public ResponseEntity<SubmittedAtt> getStudentAttemptById(@RequestParam("studentId") long sid, @RequestParam("attId") long aid){
			SubmittedAtt sub = repo.findByUserIdAndAttachmentId(sid, aid);
			
			return ResponseEntity.ok(sub);
		}
		

		
}
