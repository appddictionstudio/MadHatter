package com.Madhatter.MadHatter.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.BltBrdActRepository;
import com.Madhatter.MadHatter.models.BltBrdAct;

import org.springframework.http.ResponseEntity;


@RestController 
@RepositoryRestController 
@CrossOrigin
public class BltBrdActController {

	
	 @Autowired
     private BltBrdActRepository repo;
//	 // Get All activity //
//	 @GetMapping()
//	 @Transactional
//	 public ResponseEntity<List<BltBrdAct>> Activity() { 
//		 List<BltBrdAct> activities = repo.findAll();
//		 return ResponseEntity.ok(activities);
//		 
//	 }
//	 
	 @GetMapping("/getAllBltBrdAct")
	 ResponseEntity<List<BltBrdAct>> getAllActivity() {	
	    List<BltBrdAct> actList = repo.getAllBulletinAct();	 
	    return ResponseEntity.ok(actList);
	    }
	
	 @RequestMapping(value = "BltBrdAct/getLikesByUserId/{id}", method = RequestMethod.GET)
	 ResponseEntity<List<BltBrdAct>> getAllBulletinPOstLikes(@PathVariable Long id) {
		 List<BltBrdAct> likeList = new ArrayList<BltBrdAct>();
		 Long refCodeId = (long)3364;
		 Optional<List<BltBrdAct>> likes = repo.findByUserIdIsAndActTypeIdIs(id, refCodeId);
		 likeList = likes.get();
		 return ResponseEntity.ok(likeList);
	 }
	 
	 
}
