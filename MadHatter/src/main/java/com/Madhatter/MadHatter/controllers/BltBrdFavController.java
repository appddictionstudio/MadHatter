package com.Madhatter.MadHatter.controllers;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.BltBrdActRepository;
import com.Madhatter.MadHatter.Repositories.BltBrdFavRepository;
import com.Madhatter.MadHatter.models.BltBrdAct;
import com.Madhatter.MadHatter.models.BltBrdFav;
import com.Madhatter.MadHatter.models.BltBrdPost;
import com.Madhatter.MadHatter.models.RefCode;
import com.Madhatter.MadHatter.models.User;

@RestController 
@RepositoryRestController 
@CrossOrigin
@RequestMapping(value="/bltBrdFav")
public class BltBrdFavController {

    @Autowired
    private BltBrdFavRepository repo;
    
    @Autowired
    private BltBrdActRepository actRepo;
   
    //------------------- Create --------------------------------------------------------
    @PostMapping()
    @Transactional
    public ResponseEntity<Object> createBulletinFav(@RequestBody BltBrdFav bltBrdFav){
        
        if(repo.findById(bltBrdFav.getId()).isPresent()) {
            throw new ValidationException("Record Already Exists");
        }
        Calendar now = GregorianCalendar.getInstance();
        Timestamp postDate = new Timestamp(now.getTimeInMillis());
        
        bltBrdFav.setCreated(postDate);
        
        BltBrdFav savedBltBrdFav = repo.save(bltBrdFav);
        
        //---------------CREATE ACTIVITY ON FAVORITED-----------------------------------------------
        
        BltBrdAct activity = new BltBrdAct();
        
        //----------set user from bltBrdFav-----------------------
        User creator = new User();
        creator = bltBrdFav.getUser();
        //----------set post from bltBrdFav---------------------------
        BltBrdPost post = new BltBrdPost();
        post = bltBrdFav.getPost();
      
      //--------Set RefCode since BltBrdAct.actType is a RefCode Object and not an int---
        RefCode created = new RefCode();
//        created.setId(3365);
         created.setId(5);
        
		 activity.setActType(created);
		 activity.setUser(creator);
		 activity.setTimeStamp(postDate);
		 activity.setPost(post);
		 actRepo.save(activity);
        
       URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
       .buildAndExpand(savedBltBrdFav.getId()).toUri();
       return ResponseEntity.created(location).build();  
         
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<BltBrdFav> deleteFavorite(@PathVariable Long id) {
        System.out.println("Fetching & Deleting Favorite with id " + id);
 
        Optional<BltBrdFav> favorite = repo.findById(id); 
        if (!favorite.isPresent()) {
            System.out.println("Unable to delete. Favorite with id " + id + " not found");
            return new ResponseEntity<BltBrdFav>(HttpStatus.NOT_MODIFIED);
        }
 
        repo.deleteById(id);
        return new ResponseEntity<BltBrdFav>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(path="/{id}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<BltBrdFav> getFav(@PathVariable Long id) {
 
    	System.out.println("this is " + id);
    	
        Optional<BltBrdFav> incidentReport = repo.findById(id);
        if (!incidentReport.isPresent()) {
            return new ResponseEntity<BltBrdFav>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(incidentReport.get());
    }
    
	 @RequestMapping(method = RequestMethod.GET)
	 ResponseEntity<List<BltBrdFav>> getAllFav() {	
	    List<BltBrdFav> favList = repo.findAll();	 
	    return ResponseEntity.ok(favList);
	    }
	
	
}
