package com.Madhatter.MadHatter.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.VideoList;
import com.Madhatter.MadHatter.models.Video;

@RestController
@CrossOrigin
@RequestMapping(value="/BltBrdPost")
public class VideoController {

    @Autowired
    private VideoList repo;


    //--------------- Load -----------------------------------------------------------
     @RequestMapping(method = RequestMethod.GET)
     ResponseEntity<List<Video>> getAllBulletinPost() {    
        List<Video> postList = repo.getAllVideos();    
        return ResponseEntity.ok(postList);
        }
    
}