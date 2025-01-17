package com.jspider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.Entity.Counselor;
import com.jspider.dto.LoginRequest;
import com.jspider.enums.Status;
import com.jspider.services.CounsellorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/counselor")
public class CounselorController{
	@Autowired
	private CounsellorService counsellorService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerCounsellor(@RequestBody Counselor	counselor) {
		
		
		return counsellorService.register(counselor);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		
		
		return counsellorService.login(request);
	}
	@PostMapping("/update")
	public ResponseEntity<?> updateCounsellor(@RequestParam Integer cid,@RequestParam Status status) {
		//TODO: process POST request
		
		return counsellorService.updateStatus(cid,status);
	}
	@PostMapping("/updateStatus")
	public ResponseEntity<?> postMethodName(@RequestParam Integer cid,@RequestParam Status status) {
		//TODO: process POST request
		
		return counsellorService.updateStatus(cid, status);
	}
	@PostMapping("/getcounsellor")
	public ResponseEntity<?> getCounsellor(@RequestParam Integer cid) {
		//TODO: process POST request
		
		return counsellorService.getCounsellor(cid);
	}
	@PostMapping("/deleteCounsellor")
	public ResponseEntity<?> Delete_Counsellor(@RequestParam Integer cid) {
		//TODO: process POST request
		
		return counsellorService.deleteCounsellor(cid);
	}
	@PostMapping("/updphone")
	public ResponseEntity<?> UpdatePhone(@RequestParam Integer cid,@RequestParam Long Phone) {
		//TODO: process POST request
		
		return counsellorService.updatePhone(cid, Phone);
	}
	
	
	
	
}
