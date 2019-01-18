package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.Attachment;
import com.Madhatter.MadHatter.models.TopicAtt;



@Repository
public interface TopicAttRepository extends JpaRepository<TopicAtt, Long> {
//	
//	@Query("Select t.attachment from TopicAtt t where t.id =?1")
//	Optional<Attachment>getAttachmentByTopicAttId(Long id);
//	
	@Query("select t from TopicAtt t")
	public List<TopicAtt> getAllTopicAtt();
}
