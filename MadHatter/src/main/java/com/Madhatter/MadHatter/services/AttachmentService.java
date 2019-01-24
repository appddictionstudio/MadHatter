package com.Madhatter.MadHatter.services;

import java.util.List;
import java.io.IOException;

//import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Madhatter.MadHatter.Repositories.AttachmentRepository;
import com.Madhatter.MadHatter.Repositories.MimeTypeRepository;
import com.Madhatter.MadHatter.Repositories.SubmittedAttachmentRepository;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.MimeType;
import com.Madhatter.MadHatter.models.SubmittedAttachment;

@Service
public class AttachmentService {
	
	@Autowired
	private AttachmentRepository repo;
	
	@Autowired
	private SubmittedAttachmentRepository srepo;
	
	@Autowired
	private MimeTypeRepository mimeRepo;
	
	public Attachment storeFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Attachment att = null;
		
		try {
			att  = new Attachment();
			
			att.setAttachment(file.getBytes());
			
//			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			
			MimeType mimeType = null;
			
//			List<MimeType> mimeTypes = mimeRepo.findByExtension(extension);
//			if (mimeTypes.size() > 0) {
//				mimeType = mimeTypes.get(0);
//			}
			att.setMimeType(mimeType);
			
			att.setTitle("Attachment");
			
			return repo.save(att);
		} catch (IOException ex) {
			System.out.println("Error uploading file");
		}
		
		return att;
		
		
	}
	
	public Attachment getFile(Long id) throws Exception {
		return repo.findById(id)
				.orElseThrow(() -> new Exception("File not found"));
		
	}
	
public SubmittedAttachment storeSFile(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		SubmittedAttachment att = null;
		
		try {
			att  = new SubmittedAttachment();
			
			att.setAttachment(file.getBytes());
			
//			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			
			MimeType mimeType = null;
			
//			List<MimeType> mimeTypes = mimeRepo.findByExtension(extension);
//			if (mimeTypes.size() > 0) {
//				mimeType = mimeTypes.get(0);
//			}
			att.setMimeType(mimeType);
			
			att.setTitle("Attachment");
			
			return srepo.save(att);
		} catch (IOException ex) {
			System.out.println("Error uploading file");
		}
		
		return att;
		
		
	}
	
	public SubmittedAttachment getSFile(Long id) throws Exception {
		return srepo.findById(id)
				.orElseThrow(() -> new Exception("File not found"));
		
	}
}
