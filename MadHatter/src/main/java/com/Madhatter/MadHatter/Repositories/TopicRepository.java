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
	
	@Query("Select t from Topic t where t.mod.id = ?1")
	List<Topic> findByModId(Long Id);
	
	List<Topic> findById(Integer Id);
	
	@Query("SELECT t FROM Topic t WHERE t.id = ?1")
	Topic getById(Long Id);
	
}

