package com.Madhatter.MadHatter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.UserRepository;
import com.Madhatter.MadHatter.models.User;
import com.Madhatter.MadHatter.payloads.UserProfile;
import com.Madhatter.MadHatter.security.CurrentUser;
import com.Madhatter.MadHatter.security.UserPrincipal;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserRepository repo;

    @GetMapping("/user-profile")
    public UserProfile getUserProfile(@CurrentUser UserPrincipal currentUser) {
        return new UserProfile(currentUser.getId(), currentUser.getName(), currentUser.getUsername(),
                currentUser.getEmail(), currentUser.getAuthorities().iterator().next().toString());
    }
    
  //--------------- Load -----------------------------------------------------------
  	 @RequestMapping(method = RequestMethod.GET)
  	 ResponseEntity<List<User>> getAllBulletinPost() {	
  	    List<User> postList = repo.getAllUsers();	 
  	    return ResponseEntity.ok(postList);
  	    }
  //--------------- Search -----------------------------------------------------------
  	@RequestMapping(value = "/SearchUser/{searchString}", method = RequestMethod.GET)
	ResponseEntity<List<User>> searchUser(@PathVariable("searchString") String searchString) {
		System.out.println(searchString);

		List<User> postList = repo.findByNameContainingIgnoreCase(searchString);
		return ResponseEntity.ok(postList);
	}

}