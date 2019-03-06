package com.Madhatter.MadHatter.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.Madhatter.MadHatter.Repositories.ModulesRepository;
import com.Madhatter.MadHatter.Repositories.ResourcesRepository;
import com.Madhatter.MadHatter.models.ModAtt;
import com.Madhatter.MadHatter.models.Modules;
import com.Madhatter.MadHatter.models.Resources;
import com.Madhatter.MadHatter.models.Topic;
import com.Madhatter.MadHatter.models.TopicAtt;


@RestController
@CrossOrigin
@RequestMapping(value="/api/modules")
public class ModulesController {

	@Autowired
	private ModulesRepository repo;
	
	@Autowired
	private ResourcesRepository Rrepo;
	// ------------------- Create
//	
//    @PostMapping("/ModuleSet")
//    @Transactional
//    public ResponseEntity<Object> createBootcampModules(@RequestBody Modules module) {
//    	System.out.println("Something here");
//    	if (repo.findById(module.getId()).isPresent()) {
//			throw new ValidationException("Record Already Exists");
//		}
//    	
//    	Modules savedModule = repo.save(module);
//    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedModule.getId()).toUri();
//		return ResponseEntity.created(location).build();
//	}
	
	//--------------- Load -----------------------------------------------------------
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Modules>> getAllModules() {    
       List<Modules> moduleList = repo.getAllModules();    
       return ResponseEntity.ok(moduleList);
       }
    
 // --------------- Load Post By Id
 		// -----------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
		ResponseEntity<Optional<Modules>> getModById(@PathVariable long id) {
	Optional<Modules> post = repo.findById(id);
	return ResponseEntity.ok(post);
	}
    
    // -------------------------------GET MOD BY TYPE----------------
    @RequestMapping(value = "/bootcamp/{name}", method = RequestMethod.GET)
		ResponseEntity<List<Modules>> getModByBootcamp(@PathVariable String name) {
			List<Modules> post = repo.findByBootcampOrderByModNm(name);
			return ResponseEntity.ok(post);
	}
 	
 	//-------------------------------------put by id------------------------------------------------\\
 	
 	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateModule(@RequestBody Modules mod, @PathVariable long id){
		if(mod.getModAttachments() != null) {
			for(ModAtt attachment: mod.getModAttachments()) {
				attachment.setMod(mod);
			}
		}
		
		List<Resources> resource = Rrepo.findByModId(id);
		
		mod.setResources(resource);

		mod.setId(id);

		repo.save(mod);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
 	

}