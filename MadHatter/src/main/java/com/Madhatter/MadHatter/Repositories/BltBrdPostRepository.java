package com.Madhatter.MadHatter.Repositories;

import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.BltBrdPost;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

@Repository
public interface BltBrdPostRepository extends JpaRepository<BltBrdPost, Long> {
	Optional<BltBrdPost> findById(Long id);

	List<BltBrdPost> findAuthorById(Long id);

	@Query("select b from BltBrdPost b order by b.postDate desc")
	public List<BltBrdPost> getAllBulletinPost();

	@Query("select b from BltBrdPost b where b.hotTopicYn = 'Y' order by b.postDate desc")
	public Page<BltBrdPost> getHotTopicPosts(Pageable pageable);

//	public List<BltBrdPost> findByTextContainingIgnoreCase(String searchString);
	
	public List<BltBrdPost> findByTextIsContainingOrTopicIsContainingOrAuthorNameIsContainingAllIgnoreCase(String text, String topic, String name);
	
//	public default void updateBltPost(BltBrdPost bltBrdPost, long id) {
//		for(int i = 0; i <bltBrdPost.size(); i++) {
//			BltBrdPost b = bltBrdPost.getId(i));
//			
//			
//		}
//		
//	}

//	public List<BltBrdPost> findByHotTopicYn(String searchStringHt);
//
//	List<BltBrdPost> findAuthorById(Integer id);
	
//	public List<BltBrdPost> findByAuthorId(Long id);

}
