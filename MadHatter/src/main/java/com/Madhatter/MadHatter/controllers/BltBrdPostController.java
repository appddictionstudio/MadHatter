//package mil.af.eodims.REST.controllers;
//
//public class BltBrdPostController {
//
//}
package com.Madhatter.MadHatter.controllers;

import java.net.URI;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Madhatter.MadHatter.Repositories.BltBrdActRepository;
import com.Madhatter.MadHatter.Repositories.BltBrdPostRepository;
import com.Madhatter.MadHatter.Repositories.UserRepository;
import com.Madhatter.MadHatter.models.BltBrdAct;
import com.Madhatter.MadHatter.models.BltBrdPost;
import com.Madhatter.MadHatter.models.RefCode;
import com.Madhatter.MadHatter.models.User;
import com.Madhatter.MadHatter.models.BltBrdAtt;

@RestController
@RepositoryRestController
@CrossOrigin
public class BltBrdPostController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BltBrdPostRepository repo;

	@Autowired
	private BltBrdActRepository actRepo;
	

	// ------------------- Create
	// --------------------------------------------------------
	@RequestMapping(value = "/BltBrdPost")
	@PostMapping()
	@Transactional
	public ResponseEntity<Object> createBulletinPost(@RequestBody BltBrdPost bltBrdPost) {

		if (repo.findById(bltBrdPost.getId()).isPresent()) {
			throw new ValidationException("Record Already Exists");
		}
		
		User creator = userRepo.findById((long) bltBrdPost.getAuthor().getId());
		Calendar now = GregorianCalendar.getInstance();
		Timestamp postDate = new Timestamp(now.getTimeInMillis());


//		RefCode cat = new RefCode();
//		cat.setId(1088);
//
//		bltBrdPost.setCategory(cat);
		bltBrdPost.setPostDate(postDate);
		bltBrdPost.setAuthor(creator);
		bltBrdPost.setLikeCount(0);
		
		if(bltBrdPost.getAttachments() != null) {
			for(BltBrdAtt attachment: bltBrdPost.getAttachments()) {
				attachment.setPost(bltBrdPost);
			}
		}
		
		
		BltBrdPost savedBltBrdPost = repo.save(bltBrdPost);

		// ---------------------------Create Activity With
		// Post-----------------------------

		BltBrdAct activity = new BltBrdAct();

		// --------Set RefCode since BltBrdAct.actType is a RefCode Object and not an
		// int---

		RefCode created = new RefCode();

//		created.setId(3362);
		created.setId(2);
		activity.setActType(created);
		activity.setUser(creator);
		activity.setTimeStamp(postDate);
		activity.setPost(bltBrdPost);
		actRepo.save(activity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBltBrdPost.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	
	//---------------- Update 
	//-------------------------------------------------------------
	@RequestMapping(value = "/BltBrdPost/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Object> updatePost(@RequestBody BltBrdPost bltBrdPost, @PathVariable long id){
		bltBrdPost.setId(id);
		repo.save(bltBrdPost);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// --------------- Load
	// -----------------------------------------------------------
	@RequestMapping(value = "/BltBrdPost", method = RequestMethod.GET)
	@Transactional
	ResponseEntity<List<BltBrdPost>> getAllBulletinPost() {
		List<BltBrdPost> postList = repo.getAllBulletinPost();
		
		for(BltBrdPost post: postList) {
			List<BltBrdAtt> atts = post.getAttachments();
//			Hibernate.initialize(post.getAttachments());
		}
		
		return ResponseEntity.ok(postList);
	}
	
	//--------------- Delete
	//-------------------------------------------------------------
	@RequestMapping(value="/BltBrdPost/{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<BltBrdPost> deleteBulletinPost(@PathVariable Long id){
		repo.deleteById(id);
		return new ResponseEntity<BltBrdPost>(HttpStatus.NO_CONTENT);
	}
	
	
	// --------------- Load Post By Id
		// -----------------------------------------------------------
	@RequestMapping(value = "/BltBrdPost/{id}", method = RequestMethod.GET)
		ResponseEntity<Optional<BltBrdPost>> getBulletinPostById(@PathVariable Long id) {
			Optional<BltBrdPost> post = repo.findById(id);
		
			
			return ResponseEntity.ok(post);
	}
	
	// --------------- Load
	// HotTopic-----------------------------------------------------------

	@RequestMapping(value = "/BltBrdHotTopic", method = RequestMethod.GET)
	ResponseEntity<Page<BltBrdPost>> getHotTopicPost(@RequestParam int page, @RequestParam int size) {
		Page<BltBrdPost> hotTopicList = repo.getHotTopicPosts(PageRequest.of(page, size));
		return ResponseEntity.ok(hotTopicList);

	}
	
	// ---------------- SEARCH --------------------------

	@RequestMapping(value = "/SearchBltBrdPost/{searchString}", method = RequestMethod.GET)
	ResponseEntity<List<BltBrdPost>> searchBltBrdPost(@PathVariable("searchString") String searchString) {
		System.out.println(searchString);

		List<BltBrdPost> postList = repo.findByTextIsContainingOrTopicIsContainingOrAuthorNameIsContainingAllIgnoreCase(searchString, searchString, searchString);
		return ResponseEntity.ok(postList);
	}

//	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<BltBrdPost>> searchBltBrdPostHt(@PathVariable("searchStringHt") String searchStringHt) {
//		System.out.println(searchStringHt);

//		List<BltBrdPost> hotTopicList = repo.findByHotTopicYn(searchStringHt);
//		return ResponseEntity.ok(hotTopicList);
//	}
	
	
//	@RequestMapping(value = "/SearchBltBrdPost/{id}", method = RequestMethod.GET)
//	ResponseEntity<List<BltBrdPost>> searchBltBrdPost(@PathVariable Integer id) {
//		System.out.println(id);
//
//		List<BltBrdPost> postList = repo.findAuthorById(id);
//		return ResponseEntity.ok(postList);
//	}
	
	//----------------------------CREATE LIKE-------------------------------------//
	
	@RequestMapping(value="/BltBrdPost/like/{postId}")
	ResponseEntity<BltBrdAct> createPostLike(@PathVariable Long postId){
		
		//----------------------- GET LOGGED IN USER -----------------------------//
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String currentPrincipalName = (String) auth.getPrincipal();
		 System.out.println(currentPrincipalName);
		 
		 User creator = userRepo.findByUsername(currentPrincipalName);
//		 User user = optionalUser.get();
//		 Person person = user.getPerson();
//		 User creator = userRepo.findById(user.getId());
	
		
		// set the RefCode to a like, NEED TO CHANGE THIS, CANT HARD CODE.  //
		RefCode likeCode = new RefCode();
		likeCode.setId(3364);
		//---------------------------//
		
		Optional<BltBrdAct> likeActivity = actRepo.findByUserIdIsAndPostIdIsAndActTypeIdIs(44206L, postId, 3364L);
		
		// check if like activity exist
		if (likeActivity.isPresent()) {
			Optional<BltBrdPost> optionalPostLike = repo.findById(postId);
			BltBrdPost postLike = optionalPostLike.get();
			Integer likeCount = postLike.getLikeCount();
			likeCount--;
			postLike.setLikeCount(likeCount);
			actRepo.delete(likeActivity.get());
			repo.save(postLike);
			
			return new ResponseEntity<BltBrdAct>(HttpStatus.NO_CONTENT);
		} else {
			Optional<BltBrdPost> optionalPostLike = repo.findById(postId);
			BltBrdPost postLike = optionalPostLike.get();
			
			Calendar now = GregorianCalendar.getInstance();
			Timestamp postDate = new Timestamp(now.getTimeInMillis());
			BltBrdAct likePost = new BltBrdAct();
			
			Integer likeCount = postLike.getLikeCount();
			likeCount++;
			postLike.setLikeCount(likeCount);
			
			
			likePost.setActType(likeCode);
			likePost.setUser(creator);
			likePost.setTimeStamp(postDate);
			likePost.setPost(postLike);
			actRepo.save(likePost);
			repo.save(postLike);
			
			return ResponseEntity.ok(likePost);
		}
	}
	//----------------------END OF LIKE CREATE-------------------------------//

}