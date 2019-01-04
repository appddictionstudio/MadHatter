package com.Madhatter.MadHatter.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Madhatter.MadHatter.models.File;


@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}