package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Madhatter.MadHatter.models.MimeType;

public interface MimeTypeRepository extends JpaRepository<MimeType, Long>{
	
	Optional<MimeType> findByMimeType(String mimeType);
	
	List<MimeType> findByExtension(String extension);

}
