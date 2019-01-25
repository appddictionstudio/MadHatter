package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.BltBrdAtt;


@Repository
public interface BltBrdAttRepository extends JpaRepository<BltBrdAtt, Long> {
	
	Optional<BltBrdAtt> findById(Long id);
	
	List<BltBrdAtt> findAttById(Long id);
	
	@Query("select b from BltBrdAtt b")
	public List<BltBrdAtt> getAllBulletinAtt();
	
	List<BltBrdAtt> findByPostId(Long id);
	
	
	List<BltBrdAtt> findByFileNm(String nm);
	
//	@Query("Select b.attachment from BltBrdAtt b where b.id = ?1")
//	Optional<Attachment> getAttachmentByBltAttId(Long id);
	
//	@Query("select b from BltBrdAtt b where b.post.id =: postAttId")
//	public List<BltBrdAtt> getAttachmentById();
//

}
 