package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

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

import com.Madhatter.MadHatter.Repositories.ModAttRepository;
import com.Madhatter.MadHatter.Repositories.TopicAttRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.ModAtt;
import com.Madhatter.MadHatter.models.SubmittedAtt;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;
import com.Madhatter.MadHatter.services.AttachmentService;




@RestController
public class ModAttController {
	


@Autowired
private AttachmentService attService;

@Autowired
private ModAttRepository repo;

	//----Upload--------
	
		@PostMapping(value="/uploadMDoc")
	    public ResponseEntity<ModAtt> uploadFile(@RequestParam("file") MultipartFile file) {
	        try {
	       	 
	       	 Attachment att = attService.storeFile(file);
	       	 
	       	 ModAtt modAtt = new ModAtt();

	       	modAtt.setAttachmentId(att.getId());
	       	modAtt.setFileNm(file.getOriginalFilename());
	       	modAtt.setFileSz(new Long(att.getAttachment().length));
	      
	            repo.save(modAtt);

	            return ResponseEntity.ok(modAtt);
	        } catch (Exception e) {
	            throw e;
	        }
	    }
		
		//----------------Downloading----------
		
				@RequestMapping(value = "/ModAtt/downloadDoc/{id}", method = RequestMethod.GET)
				@ResponseBody
				public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) throws Exception {
					
					ModAtt attachment = repo.getOne(id);
					Attachment att = attService.getFile(attachment.getAttachmentId());
					response.setContentType("application/octet-stream");
					response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
				    response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getFileNm());

				    
				    /** assume that below line gives you file content in byte array **/
				    byte[] binary = att.getAttachment();
				    
//				    int x = fis.available();
//				    byte byteArray[] = new byte[x];
//				    logger.info(" File size :"+byteArray.length);
//				    fis.read(byteArray);

				    response.getOutputStream().write(binary);
				    response.flushBuffer();
//				    fis.close();
				}
				
			//-------------------REMOVE-------------------------------
			@RequestMapping(value = "/ModAtt/remove/{id}", method = RequestMethod.DELETE)
			@ResponseBody
			public void removeModAtt(@PathVariable Long id) {
				repo.deleteById(id);
			}

			@RequestMapping(value = "/studentModsUI", method = RequestMethod.GET)
			@ResponseBody
			ResponseEntity<Optional<ModAtt>> getStudentMods(){
				Optional<ModAtt> modAtt = repo.findById((long) 2018);
				return ResponseEntity.ok(modAtt);
			}
			
			@RequestMapping(value = "/studentModsASD", method = RequestMethod.GET)
			@ResponseBody
			ResponseEntity<Optional<ModAtt>> getStudentModASD(){
				Optional<ModAtt> modAtt = repo.findById((long) 2008);
				return ResponseEntity.ok(modAtt);
			}
		
}