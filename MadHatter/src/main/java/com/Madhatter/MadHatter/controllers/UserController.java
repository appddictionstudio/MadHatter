package com.Madhatter.MadHatter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
  	 ResponseEntity<List<User>> getAllMhUsers() {	
  	    List<User> postList = repo.getAllUsers();	 
  	    return ResponseEntity.ok(postList);
  	    }
  //--------------- Search -----------------------------------------------------------
  	@RequestMapping(value = "/SearchUser/{searchString}", method = RequestMethod.GET)
	ResponseEntity<List<User>> searchUser(@PathVariable("searchString") String searchString) {

		List<User> postList = repo.findByNameContainingIgnoreCase(searchString);
		return ResponseEntity.ok(postList);
	}
  	
  	@RequestMapping(value = "/SearchUsername/{searchString}", method = RequestMethod.GET)
	ResponseEntity<User> searchUsername(@PathVariable("searchString") String searchString) {

		User postList = repo.findByUsernameContainingIgnoreCase(searchString);
		return ResponseEntity.ok(postList);
	}
  	
  	@RequestMapping(value = "/SearchEmail/{searchString}", method = RequestMethod.GET)
	ResponseEntity<User> searchEmail(@PathVariable("searchString") String searchString) {

		User postList = repo.findByEmailContainingIgnoreCase(searchString);
		return ResponseEntity.ok(postList);
	}
  	
  	@RequestMapping(value = "/SearchUserPage", method = RequestMethod.GET)
	ResponseEntity<Page<User>> searchUserPage(@RequestParam("name") String searchString, @RequestParam int page, @RequestParam int size) {

		Page<User> postList = repo.findByNameContainingIgnoreCaseOrderByName(searchString, PageRequest.of(page, size));
		return ResponseEntity.ok(postList);
	}
  	
  	@RequestMapping(value = "/allPage", method = RequestMethod.GET)
	ResponseEntity<Page<User>> searchAllUserPage(@RequestParam int page, @RequestParam int size) {
  		System.out.println(page + " " + size);

		Page<User> postList = repo.findAll(PageRequest.of(page, size));
		return ResponseEntity.ok(postList);
	}

}