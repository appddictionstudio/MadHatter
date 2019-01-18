package com.Madhatter.MadHatter.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Madhatter.MadHatter.models.Attachment;


	
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	
//	List<Attachment> findByPostId(Long PostId);
	Optional<Attachment> findById(Long postId);
}
