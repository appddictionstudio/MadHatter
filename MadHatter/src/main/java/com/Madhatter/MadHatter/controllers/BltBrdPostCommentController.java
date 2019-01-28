package com.Madhatter.MadHatter.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.BltBrdActRepository;
import com.Madhatter.MadHatter.Repositories.BltBrdPostCommentRespository;
import com.Madhatter.MadHatter.Repositories.BltBrdPostRepository;
import com.Madhatter.MadHatter.Repositories.UserRepository;
import com.Madhatter.MadHatter.models.BltBrdAct;
import com.Madhatter.MadHatter.models.BltBrdPost;
import com.Madhatter.MadHatter.models.BltBrdPostComment;
import com.Madhatter.MadHatter.models.RefCode;
import com.Madhatter.MadHatter.models.User;

@RestController
@RepositoryRestController
@CrossOrigin
public class BltBrdPostCommentController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BltBrdPostCommentRespository repo;
	
	@Autowired
	private BltBrdPostRepository postRepo;
	
	@Autowired
	private BltBrdActRepository actRepo;
	
	//------------------ Create
	//--------------------------------------------------
	@RequestMapping(value = "/BltBrdPostComment/{postId}", method = RequestMethod.POST)
	@Transactional
	public  ResponseEntity<Optional<BltBrdPost>> createPostComment(@RequestBody BltBrdPostComment bltBrdPostComment, @PathVariable Long postId){
		if (repo.findById(bltBrdPostComment.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}
		
		Calendar now = GregorianCalendar.getInstance();
		Timestamp postDate = new Timestamp(now.getTimeInMillis());
		
		//----------------------GET CURRENTLY LOGGED IN USER -----------------------------------\\
		User creator = userRepo.findById((long) bltBrdPostComment.getId());
		
		bltBrdPostComment.setPostDate(postDate);
		bltBrdPostComment.setAuthor(creator);
		
		BltBrdPost bltBrdPost = new BltBrdPost();
		bltBrdPost.setId(postId);
		
		bltBrdPostComment.setPost(bltBrdPost);
		
		
		BltBrdPostComment savedPostComment = repo.save(bltBrdPostComment);
		Optional<BltBrdPost> post = postRepo.findById(postId);
		
		BltBrdAct activity = new BltBrdAct();
		
		BltBrdPost currentPost = new BltBrdPost();
		currentPost = bltBrdPostComment.getPost();
		RefCode created = new RefCode();
//		created.setId(3363);
		created.setId(3);
        
		activity.setActType(created);
		activity.setUser(creator);
		activity.setTimeStamp(postDate);
		activity.setPost(currentPost);
		actRepo.save(activity);
		
//		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/BltBrdPost/{id}")
//		.buildAndExpand(postId).toUri();
		return ResponseEntity.ok(post);
	}
	
	//----------------- Load Post by ID
	//---------------------------------------------------
	@RequestMapping(value="/BltBrdPostComment/{id}", method = RequestMethod.GET)
	ResponseEntity<Optional<BltBrdPost>> getPostByCommentId(@PathVariable long id){
		Optional<BltBrdPost> post = repo.getCommentByPostId(id);
		
		return ResponseEntity.ok(post);
	}
	
	
	//----------------- Load 
	//---------------------------------------------------
	@RequestMapping(value="/BltBrdPostComment", method = RequestMethod.GET)
	ResponseEntity<List<BltBrdPostComment>> getAllPostComment(){
		List<BltBrdPostComment> postCommentList = repo.getAllPostComment();
		return ResponseEntity.ok(postCommentList);
	}
	

}
