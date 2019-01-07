package com.Madhatter.MadHatter.Repositories;

import com.Madhatter.MadHatter.models.Video;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
* Generated by Spring Data Generator on 03/12/2018
*/
@Repository
public interface VideoList extends JpaRepository<Video, Long> {
	@Query("select v from video v")
	public List<Video> getAllVideos();
}
