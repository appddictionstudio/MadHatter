package com.Madhatter.MadHatter.Repositories;

import com.Madhatter.MadHatter.models.Modules;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 03/12/2018
*/
@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {
	@Query("select m from Modules m")
	public List<Modules> getAllModules();
}