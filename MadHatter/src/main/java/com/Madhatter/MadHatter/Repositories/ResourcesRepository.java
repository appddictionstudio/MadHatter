package com.Madhatter.MadHatter.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.Resources;
import com.Madhatter.MadHatter.models.Modules;



@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
	@Query("Select r.mod from Resources r where r.id = ?1")
	Optional<Modules> findByResourceId(Long Id);
	
	List<Resources> findById(Integer Id);
	
	@Query("SELECT r FROM Resources r WHERE r.mod.id = ?1")
	List<Resources> findByModId(Long id);
	
	@Query("SELECT r.mod FROM Resources r")
	List<Modules> findByAll();
}

