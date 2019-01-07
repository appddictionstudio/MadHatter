package com.Madhatter.MadHatter.controllers;
import java.net.URI;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mil.af.eodims.REST.models.BltBrdPost;
import mil.af.eodims.REST.models.EodTechnician;
import mil.af.eodims.REST.models.UnitsOfMeasure;
import mil.af.eodims.REST.repositories.BltBrdPostRepository;


@RestController
@RepositoryRestController
@CrossOrigin
@RequestMapping(value="/BltBrdPost")
public class BltBrdPostController {

    @Autowired
    private BltBrdPostRepository repo;

    //------------------- Create --------------------------------------------------------
    @PostMapping()
    @Transactional
    public ResponseEntity<Object> createBulletinPost(@RequestBody BltBrdPost bltBrdPost){
       

        if(repo.findById(bltBrdPost.getId()).isPresent()) {
            throw new ValidationException("Record Already Exists");
        }
        Calendar now = GregorianCalendar.getInstance();
        Timestamp postDate = new Timestamp(now.getTimeInMillis());

        EodTechnician creator = new EodTechnician();
         creator.setId(44206);
        
        bltBrdPost.setPostDate(postDate);
        bltBrdPost.setAuthor(creator);
        BltBrdPost savedBltBrdPost = repo.save(bltBrdPost);
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
       .buildAndExpand(savedBltBrdPost.getId()).toUri();
       return ResponseEntity.created(location).build();


    }

    //--------------- Load -----------------------------------------------------------
     @RequestMapping(method = RequestMethod.GET)
     ResponseEntity<List<BltBrdPost>> getAllBulletinPost() {    
        List<BltBrdPost> postList = repo.getAllBulletinPost();    
        return ResponseEntity.ok(postList);
        }
    
}