package com.jspider.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.Entity.Counselor;
import com.jspider.Entity.Enquiry;
import com.jspider.Exception.CounsellorNotFound;
import com.jspider.Exception.EnquiryNotFound;
import com.jspider.Repository.CounsellorRepository;
import com.jspider.Repository.EnquiryRepository;
import com.jspider.dto.EnquiryDto;
import com.jspider.dto.FilterDto;
import com.jspider.enums.ClassMode;
import com.jspider.responseStructure.ResponseStructure;

@Service
public class EnquiryServices implements EnquiryServiceInterface{

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CounsellorRepository counsellorRepository;

	public ResponseEntity<?> addEnquiry(Integer cid, Enquiry enquiry) {
		// TODO Auto-generated method stub
		Counselor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor Does not exist"));
		Optional<Enquiry> opt = enquiryRepository.findByEmail(enquiry.getEmail());

		if (opt.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Enquiry already added");
			rs.setData(enquiry.getEmail());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			enquiry.setCounselor(counsellor);
			Enquiry save = enquiryRepository.save(enquiry);

			EnquiryDto dto = new EnquiryDto();

			BeanUtils.copyProperties(save, dto);

			ResponseStructure<EnquiryDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Enquiry added successfully");
			rs.setData(dto);

			return new ResponseEntity<ResponseStructure<EnquiryDto>>(rs, HttpStatus.OK);
		}

	}

//	public ResponseEntity<?> getEnquiries(Integer eid) {
//
//
//		  Enquiry enquiry = enquiryRepository.findById(eid)
//		            .orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));
//
//		    ResponseStructure<EnquiryDto> response = new ResponseStructure<>();
//		    response.setStatusCode(HttpStatus.OK.value());
//		    response.setMessage("Enquiry retrieved successfully");
//		    
//		    EnquiryDto dto = new EnquiryDto();
//		    BeanUtils.copyProperties(enquiry, dto);
//		    response.setData(dto);
//
//		    return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	public ResponseEntity<?> filter(FilterDto dto) {
		// TODO Auto-generated method stub
		Enquiry enquiry = new Enquiry();

		BeanUtils.copyProperties(dto, enquiry);

		Example<Enquiry> of = Example.of(enquiry);

		List<Enquiry> all = enquiryRepository.findAll(of);

		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Retrieved Enquiries");
		rs.setData(all);

		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<?> updateClassMode(Integer eid, ClassMode classMode) {
		// TODO Auto-generated method stub
		 Enquiry enquiry = enquiryRepository.findById(eid)
		            .orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));

		    enquiry.setClassMode(classMode);
		    Enquiry updatedEnquiry = enquiryRepository.save(enquiry);

		    ResponseStructure<EnquiryDto> response = new ResponseStructure<>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Class mode updated successfully");
		    
		    EnquiryDto dto = new EnquiryDto();
		    BeanUtils.copyProperties(updatedEnquiry, dto);
		    response.setData(dto);

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
	

	@Override
	public ResponseEntity<?> updateFees(Integer eid, Double fees) {
		// TODO Auto-generated method stub
		Enquiry enquiry = enquiryRepository.findById(eid)
	            .orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));

	    enquiry.setFees(fees);
	    Enquiry updatedEnquiry = enquiryRepository.save(enquiry);

	    ResponseStructure<EnquiryDto> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Fees updated successfully");
	    
	    EnquiryDto dto = new EnquiryDto();
	    BeanUtils.copyProperties(updatedEnquiry, dto);
	    response.setData(dto);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteEnquiry(Integer cid, Integer eid) {
		// TODO Auto-generated method stub
		 Counselor counselor = counsellorRepository.findById(cid)
		            .orElseThrow(() -> new CounsellorNotFound("Counselor does not exist"));

		    Enquiry enquiry = enquiryRepository.findById(eid)
		            .orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));

		  

		    enquiryRepository.delete(enquiry);
		    
		    ResponseStructure<String> response = new ResponseStructure<>();
		    response.setStatusCode(HttpStatus.OK.value());
		    response.setMessage("Enquiry deleted successfully");
		    response.setData("Enquiry with ID " + eid + " has been deleted.");

		    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getEnquiry(Integer eid) {
		Enquiry enquiry = enquiryRepository.findById(eid)
	            .orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));

	    ResponseStructure<EnquiryDto> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Enquiry retrieved successfully");
	    
	    EnquiryDto dto = new EnquiryDto();
	    BeanUtils.copyProperties(enquiry, dto);
	    response.setData(dto);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	@Override
	public ResponseEntity<?> getEnquiries(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
