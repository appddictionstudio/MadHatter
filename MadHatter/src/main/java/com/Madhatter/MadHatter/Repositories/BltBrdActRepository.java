package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Madhatter.MadHatter.models.BltBrdAct;

@Repository
public interface BltBrdActRepository extends JpaRepository<BltBrdAct, Long>{
	
	@Query("SELECT b FROM BltBrdAct b order by b.timeStamp desc")
	List<BltBrdAct> getAllBulletinAct();
	
	Optional<List<BltBrdAct>> findByUserIdIsAndActTypeIdIs(Long user, Long actTypeId);
	
	Optional<BltBrdAct> findByUserIdIsAndPostIdIsAndActTypeIdIs(Long user, Long postId, Long actTypeId);

}
