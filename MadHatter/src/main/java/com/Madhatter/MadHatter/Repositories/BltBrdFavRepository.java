package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Madhatter.MadHatter.models.BltBrdFav;

public interface BltBrdFavRepository extends JpaRepository<BltBrdFav, Long> {
	Optional<BltBrdFav> findById(Long id);
	
	List<BltBrdFav> findByUserIdOrderById(Long eodTechId);
}
