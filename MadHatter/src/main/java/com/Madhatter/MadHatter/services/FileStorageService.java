package com.Madhatter.MadHatter.services;

import com.Madhatter.MadHatter.Repositories.FileRepository;
import com.Madhatter.MadHatter.models.File;
import com.Madhatter.MadHatter.models.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file, Form form) throws Exception {
        String name = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (name.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid characters");
            }

            File newFile = new File(name, file.getContentType(), name, file.getBytes());

            newFile.setForm(form);
            return fileRepository.save(newFile);
        } catch (Exception e) {
            throw new Exception("Could not store file" + name + ". Please try again", e);
        }
    }

    public File getFile(Long id) throws Exception {
        return fileRepository.findById(id).orElseThrow(() -> new Exception("File not found!"));
    }
}