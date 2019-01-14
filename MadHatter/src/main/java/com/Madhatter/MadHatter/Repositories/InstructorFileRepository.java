package com.Madhatter.MadHatter.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.InstructorFiles;


@Repository
public interface InstructorFileRepository extends JpaRepository<InstructorFiles, Long> {

}