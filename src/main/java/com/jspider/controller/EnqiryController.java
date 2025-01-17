package com.jspider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.Entity.Enquiry;
import com.jspider.dto.FilterDto;
import com.jspider.enums.ClassMode;
import com.jspider.services.EnquiryServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/enquiry")
public class EnqiryController {
	
	@Autowired
	private EnquiryServices enquiryServices;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEnquiry(@RequestParam Integer cid,@RequestBody Enquiry enquiry) {
		return enquiryServices.addEnquiry(cid,enquiry);
	}
	
//	@GetMapping("/all")
//	public ResponseEntity<?> getEnquiriesByCid(@RequestParam Integer eid) {
//		return enquiryServices.getEnquiries(eid);
//	}

	@GetMapping("/filter")
	public ResponseEntity<?> filter(@RequestBody FilterDto dto) {
		return enquiryServices.filter(dto);
	}
	@PostMapping("/fees")
	public ResponseEntity<?> updatefees(@RequestParam Integer eid,@RequestParam Double fees) {
		//TODO: process POST request
		
		return enquiryServices.updateFees(eid, fees);
		
	}
	@PostMapping("/delete")
	public ResponseEntity<?> DelteEnquiry(@RequestParam Integer cid,@RequestParam Integer eid) {
		//TODO: process POST request
		
		return enquiryServices.deleteEnquiry(cid, eid);
	}
	
	@PostMapping("/get")
	public ResponseEntity<?> GetEnquiey(@RequestParam Integer eid) {
		//TODO: process POST request
		
		return enquiryServices.getEnquiry(eid);
	}
	@PostMapping("/classmode")
	public ResponseEntity<?> updateClassMode(@RequestParam Integer eid,@RequestParam ClassMode classMode) {
		//TODO: process POST request
		
		return enquiryServices.updateClassMode(eid, classMode);
		
	}
	
}
