package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.BltBrdPost;
import com.Madhatter.MadHatter.models.BltBrdPostComment;

@Repository
public interface BltBrdPostCommentRespository extends JpaRepository<BltBrdPostComment, Long>{
	Optional<BltBrdPostComment> findById(Long id);
	
	@Query("select b from BltBrdPostComment b")
	public List<BltBrdPostComment> getAllPostComment();
	
	@Query("Select b.post from BltBrdPostComment b where b.id = ?1")
	Optional<BltBrdPost> getCommentByPostId(Long id);
	

}
