package com.jspider.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jspider.responseStructure.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(CounsellorNotFound.class)
	public ResponseEntity<ResponseStructure<String>> catchCounsellorNotFound(CounsellorNotFound exception) {
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Counsellor Not Found/Registered");

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
}
