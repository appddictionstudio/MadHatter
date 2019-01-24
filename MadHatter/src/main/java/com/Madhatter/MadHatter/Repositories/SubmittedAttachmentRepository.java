package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.SubmittedAttachment;


	
public interface SubmittedAttachmentRepository extends JpaRepository<SubmittedAttachment, Long>{
	
//	List<Attachment> findByPostId(Long PostId);
	Optional<SubmittedAttachment> findById(Long postId);
	
	@Query("select a from SubmittedAttachment a")
	public List<SubmittedAttachment> getAllAttachments();
}
