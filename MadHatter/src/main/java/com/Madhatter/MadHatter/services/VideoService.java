package com.Madhatter.MadHatter.services;

import com.Madhatter.MadHatter.Repositories.VideoList;
import com.Madhatter.MadHatter.models.Video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoList fileRepository;

    public Video getVideo(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(() -> new Exception("File not found!"));
    }
}