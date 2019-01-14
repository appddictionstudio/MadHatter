package com.Madhatter.MadHatter.services;

import com.Madhatter.MadHatter.Repositories.InstructorFileRepository;
import com.Madhatter.MadHatter.models.InstructorFiles;
import com.Madhatter.MadHatter.models.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InstructorFileStorageService {

    @Autowired
    private InstructorFileRepository instructorFileRepository;

    public InstructorFiles storeFile(MultipartFile file, Form form) throws Exception {
        String name = StringUtils.cleanPath(file.getOriginalFilename());
    	Number number = null;
        try {
            if (name.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid characters");
            }

            
			InstructorFiles newFile = new InstructorFiles(name, file.getContentType(), name, number, file.getBytes());

            newFile.setForm(form);
            return instructorFileRepository.save(newFile);
        } catch (Exception e) {
            throw new Exception("Could not store file" + name + ". Please try again", e);
        }
    }

    public InstructorFiles getFile(Long id) throws Exception {
        return instructorFileRepository.findById(id).orElseThrow(() -> new Exception("File not found!"));
    }
}