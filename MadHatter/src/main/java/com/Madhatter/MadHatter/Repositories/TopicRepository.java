package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.Modules;



@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	
	@Query("select t from Topic t")
	public List<Topic> getAllTopics();
	
	@Query("Select t.mod from Topic t where t.id = ?1")
	Optional<Modules> findByModId(Long Id);
	
	List<Topic> findById(Integer Id);
	
}

