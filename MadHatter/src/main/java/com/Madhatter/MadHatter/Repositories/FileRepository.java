package com.Madhatter.MadHatter.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.File;
import com.Madhatter.MadHatter.models.Topic;


@Repository
public interface FileRepository extends JpaRepository<File, Long> {

	@Query("Select f.topic from File f where f.id = ?1")
	Optional<File> getFileByTopicId(Long id);
}