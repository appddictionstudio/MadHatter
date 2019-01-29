package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.SubmittedAtt;
import com.Madhatter.MadHatter.models.TopicAtt;




@Repository
public interface SubmittedAttRepository extends JpaRepository<SubmittedAtt, Long> {

	@Query("select t from SubmittedAtt t")
	public List<SubmittedAtt> getAllSubmittedAtt();
	
	@Query("Select t from SubmittedAtt t where t.topicatt.id = ?1")
	List<SubmittedAtt> findByAttId(Long id);
	

}