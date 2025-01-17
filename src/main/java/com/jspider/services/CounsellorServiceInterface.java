package com.jspider.services;


import org.springframework.http.ResponseEntity;

import com.jspider.dto.*;
import com.jspider.Entity.*;
import com.jspider.enums.Status;


public interface CounsellorServiceInterface {

	ResponseEntity<?> register(Counselor counsellor);

	ResponseEntity<?> login(LoginRequest request);

	ResponseEntity<?> updateStatus(Integer cid, Status status);

	ResponseEntity<?> updatePhone(Integer cid, Long phone);

	ResponseEntity<?> getCounsellor(Integer cid);
	ResponseEntity<?> deleteCounsellor(Integer cid);

}
