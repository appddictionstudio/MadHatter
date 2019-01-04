package com.Madhatter.MadHatter.Repositories;

import com.Madhatter.MadHatter.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}