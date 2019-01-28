package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Madhatter.MadHatter.models.Attachment;


	
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	
//	List<Attachment> findByPostId(Long PostId);
	Optional<Attachment> findById(Long postId);
	
	Optional<Attachment> findById(Attachment attachment);
	
	@Query("select a from Attachment a")
	public List<Attachment> getAllAttachments();
}
