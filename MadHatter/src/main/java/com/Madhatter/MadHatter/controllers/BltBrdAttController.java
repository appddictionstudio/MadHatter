package com.Madhatter.MadHatter.controllers;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.Madhatter.MadHatter.Repositories.BltBrdAttRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.BltBrdAtt;
import com.Madhatter.MadHatter.models.BltBrdPost;
import com.Madhatter.MadHatter.models.RefCode;
import com.Madhatter.MadHatter.services.AttachmentService;


@RestController
@RepositoryRestController
@CrossOrigin
public class BltBrdAttController {

	@Autowired
	private BltBrdAttRepository repo;

	@Autowired
	private AttachmentService attService;

	// ------------------- Create------------------------------
	// --------------------------------------------------------
	@PostMapping(value = "/BltBrdAtt")
	@Transactional
	public ResponseEntity<Object> createBulletinAtt(@RequestBody BltBrdAtt bltBrdAtt) {

		if (repo.findById(bltBrdAtt.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}

		BltBrdAtt savedBltBrdAtt = repo.save(bltBrdAtt);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBltBrdAtt.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// -------------- Upload-----------------------------------
	// --------------------------------------------------------

	@PostMapping(value="/uploadBltDoc")
     public ResponseEntity<BltBrdAtt> uploadFile(@RequestParam("file") MultipartFile file) {
         try {
        	 
        	 Attachment att = attService.storeFile(file);
        	 
        	 BltBrdAtt bltBrdAtt = new BltBrdAtt();

        	 bltBrdAtt.setAttachment(att);
             bltBrdAtt.setFileNm(file.getOriginalFilename());
             bltBrdAtt.setFileSz(new Long(att.getAttachment().length));
             

             
             RefCode docType = new RefCode();
             docType.setId(307100);
             bltBrdAtt.setDocType(docType);
             
             repo.save(bltBrdAtt);

             return ResponseEntity.ok(bltBrdAtt);
         } catch (Exception e) {
             throw e;
         }
     }

	// --------------- Load---------------------------------------
	// -----------------------------------------------------------
	@RequestMapping(value = "/BltBrdAtt", method = RequestMethod.GET)
	ResponseEntity<List<BltBrdAtt>> getAllBulletinAtt() {
		List<BltBrdAtt> attList = repo.getAllBulletinAtt();
		return ResponseEntity.ok(attList);
	};
	
	
	// --------------- Load Attachment By BltBrdAtt Id ---------------------------------------
	// -----------------------------------------------------------
//	@RequestMapping(value = "/BltBrdAtt/{id}", method = RequestMethod.GET)
//	ResponseEntity<Resource> getAttachmentByBltAttId(@PathVariable long id) {
//		Optional<Attachment> attachment = repo.getAttachmentByBltAttId(id);
//		
//		String ext = attachment.get().getMimeType().getExtension();
//		String mimeType = attachment.get().getMimeType().getMimeType();
//		
//		return ResponseEntity.ok()
//				.contentType(MediaType.parseMediaType(mimeType))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"attachment.\"" + ext + "\"")
//				.body(new ByteArrayResource(attachment.get().getAttachment()));
//	};

	// ------------- Load Photo Attachment-----------------------
	// ----------------------------------------------------------
	
	@RequestMapping(value = "/BltBrdAtt/getAttByPost", method = RequestMethod.POST)
	ResponseEntity<List<BltBrdAtt>> getAttByPost(@RequestBody BltBrdPost post) {
		long id = post.getId();
		
		List<BltBrdAtt> attachments = repo.findByPostId(post.getId());
		
		return ResponseEntity.ok(attachments);
	};
	
	//--------------- Download Attachment -------------------
	//-------------------------------------------------------
//	@RequestMapping(value="/BltBrdAtt/downloadDoc/{id}", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//	public @ResponseBody byte[] getFile(@PathVariable long id) throws Exception {
//		Attachment att = attService.getFile(id);
//		return att.getAttachment();
//	}	
	
//	@RequestMapping(path = "/BltBrdAtt/downloadDoc/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Resource> download(@PathVariable Long id) throws Exception {
//		Attachment att = attService.getFile(id);
//
//	    InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(att.getAttachment()));
//
//	    return ResponseEntity.ok()
//	    		.header("Access-Control-Expose-Headers", "Content-Disposition")
//	    		.header("Content-Disposition", "attachment; filename=" + att.getTitle())
//	            .contentLength(att.getAttachment().length)
//	            .contentType(MediaType.parseMediaType("application/octet-stream"))
//	            .body(resource);
//	}
	
//	@RequestMapping(value = {"/BltBrdAtt/downloadDoc/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public HttpEntity<byte[]> downloadExcelReport(@PathVariable Long id) throws Exception {
//		
//		BltBrdAtt att = attService.getFile(id);
//	 
//	    /** assume that below line gives you file content in byte array **/
//	    byte[] binary = att.getAttachment().getAttachment();
//	    // prepare response
//	    HttpHeaders header = new HttpHeaders();
////	    header.setContentType(new MediaType("application", "octet-stream"));
//	    header.set("Access-Control-Expose-Headers", "Content-Disposition");
//	    header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ att.getFileNm());
//	    header.setContentLength(att.getFileSz());
//	 
//	    return new HttpEntity<byte[]>(binary, header);
//	}
//	
	@RequestMapping(value ="/BltBrdAtt/downloadDoc/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws Exception{
		BltBrdAtt att = attService.getBFile(id);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
	    response.setHeader("Content-Disposition", "attachment; filename=" + att.getFileNm());

	    
	    /** assume that below line gives you file content in byte array **/
	    byte[] binary = att.getAttachment().getAttachment();
	    
//	    int x = fis.available();
//	    byte byteArray[] = new byte[x];
//	    logger.info(" File size :"+byteArray.length);
//	    fis.read(byteArray);

	    response.getOutputStream().write(binary);
	    response.flushBuffer();
//	    fis.close();
	}
}
	

