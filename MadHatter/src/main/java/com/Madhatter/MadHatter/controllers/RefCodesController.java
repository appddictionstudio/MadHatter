package com.Madhatter.MadHatter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Madhatter.MadHatter.Repositories.RefCodeRepository;
import com.Madhatter.MadHatter.models.RefCode;

@RestController
@RequestMapping(value = "/RefCode")
public class RefCodesController {
	
	@Autowired
	private RefCodeRepository refCodeRepo;

	@GetMapping("/findByParentNm")
	public ResponseEntity<List<RefCode>> findByParentName(@RequestParam String nm) {
		return ResponseEntity.ok(refCodeRepo.findByParentRefCodeNmAndObsoleteYnAllIgnoreCaseOrderBySortOrder(nm, "N"));
	}
	
	@RequestMapping(value="/getBltCategory", method = RequestMethod.GET)
	 ResponseEntity<List<RefCode>> getBltCategory(){
		 List<RefCode> bltCategoryList = refCodeRepo.getBltCategory();
		 return ResponseEntity.ok(bltCategoryList);
		 
	 }
}
