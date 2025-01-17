package com.jspider.services;

import org.springframework.http.ResponseEntity;

import com.jspider.Entity.Enquiry;
import com.jspider.dto.FilterDto;
import com.jspider.enums.ClassMode;

public interface EnquiryServiceInterface {

ResponseEntity<?> addEnquiry(Integer cid,Enquiry enquiry);
	
	ResponseEntity<?> updateClassMode(Integer eid,ClassMode classMode);
	
	ResponseEntity<?> updateFees(Integer eid,Double fees);
	
	ResponseEntity<?> deleteEnquiry(Integer cid,Integer eid);
	
	ResponseEntity<?> getEnquiry(Integer eid);
	
	ResponseEntity<?> getEnquiries(Integer cid);
	
	ResponseEntity<?> filter(FilterDto dto);
	
	
}
