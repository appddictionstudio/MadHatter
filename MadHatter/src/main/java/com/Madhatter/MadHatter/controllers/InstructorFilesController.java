//package com.Madhatter.MadHatter.controllers;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.ValidationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.apache.commons.io.FilenameUtils;
//
//import com.Madhatter.MadHatter.Repositories.InstructorFileRepository;
//import com.Madhatter.MadHatter.models.Attachment;
//import com.Madhatter.MadHatter.models.InstructorFiles;
//import com.Madhatter.MadHatter.services.AttachmentService;
//
//@RestController
//@CrossOrigin
//public class InstructorFilesController {
//
//	@Autowired
//	private InstructorFileRepository repo;
//
//	@Autowired
//	private Attachment attService;
//
//	// ------------------- Create------------------------------
//	// --------------------------------------------------------
////	@PostMapping(value = "/BltBrdAtt")
////	@Transactional
////	public ResponseEntity<Object> createBulletinAtt(@RequestBody BltBrdAtt bltBrdAtt) {
////
////		if (repo.findById(bltBrdAtt.getId()).isPresent()) {
////			throw new ValidationException("Record Already Exists");
////		}
////
////		BltBrdAtt savedBltBrdAtt = repo.save(bltBrdAtt);
////		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
////				.buildAndExpand(savedBltBrdAtt.getId()).toUri();
////		return ResponseEntity.created(location).build();
////	}
//
//	// -------------- Upload-----------------------------------
//	// --------------------------------------------------------
//
//	@PostMapping(value="/uploadDoc")
//     public ResponseEntity<InstructorFiles> uploadFile(@RequestParam("file") MultipartFile file) {
//         try {
//        	 
//        	 Attachment att = attService.storeFile(file);
//        	 
//        	 InstructorFiles instructorfiles = new InstructorFiles();
//
//        	 instructorfiles.setAttachment(att);
//        	 instructorfiles.setFileName(file.getOriginalFilename());
//        	 instructorfiles.getFileType(new FilenameUtils.getExtension(file.getOriginalFilename()));
//             RefCode docType = new RefCode();
//             docType.setId(307100);
//             bltBrdAtt.setDocType(docType);
//             
//             repo.save(bltBrdAtt);
//
//             return ResponseEntity.ok(bltBrdAtt);
//         } catch (Exception e) {
//             throw e;
//         }
//     }
//	
//	@RequestMapping(value = "/BltBrdAtt", method = RequestMethod.GET)
//	ResponseEntity<List<BltBrdAtt>> getAllBulletinAtt() {
//		List<BltBrdAtt> attList = repo.getAllBulletinAtt();
//		return ResponseEntity.ok(attList);
//	};
//}