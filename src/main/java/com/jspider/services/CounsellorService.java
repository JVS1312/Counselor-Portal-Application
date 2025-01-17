package com.jspider.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.Entity.Counselor;
import com.jspider.Exception.CounsellorNotFound;
import com.jspider.Repository.CounsellorRepository;
import com.jspider.dto.CounsellorDto;
import com.jspider.dto.LoginRequest;
import com.jspider.enums.Status;
import com.jspider.responseStructure.ResponseStructure;

@Service
public class CounsellorService implements CounsellorServiceInterface{
	@Autowired
	private CounsellorRepository counsellorRepository;

	@Override
	public ResponseEntity<?> register(Counselor counsellor) {
		Optional<Counselor> byEmail = counsellorRepository.findByEmail(counsellor.getEmail());
		if (byEmail.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Already Registered");
			rs.setData(counsellor.getEmail());
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			Counselor save = counsellorRepository.save(counsellor);

			CounsellorDto dto = new CounsellorDto();

			BeanUtils.copyProperties(save, dto);

			ResponseStructure<CounsellorDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Registered Successfully");
			rs.setData(dto);

			return new ResponseEntity<ResponseStructure<CounsellorDto>>(rs, HttpStatus.OK);

		}
		
	}

	@Override
	public ResponseEntity<?> login(LoginRequest request) {
		// TODO Auto-generated method stub
		Counselor counsellor = counsellorRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is Not Registered"));
		ResponseStructure<String> rs = new ResponseStructure<>();
		if (counsellor.getPassword().equals(request.getPassword())) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Login Success");
			rs.setData(request.getEmail());
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		}
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Invalid Password");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
	

	@Override
	public ResponseEntity<?> updateStatus(Integer cid, Status status) {
		// TODO Auto-generated method stub
		Counselor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(()-> new CounsellorNotFound("Counsellor Not registered"));
		counsellor.setStatus(status);
		Counselor save = counsellorRepository.save(counsellor);
		ResponseStructure<Status> rs=new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Status updated successfully");
		rs.setData(save.getStatus());
		return new ResponseEntity<ResponseStructure<Status>>(rs,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updatePhone(Integer cid, Long phone) {
	    Counselor counselor = counsellorRepository.findById(cid)
	            .orElseThrow(() -> new CounsellorNotFound("Counselor Not registered"));
	    
	    counselor.setPhone(phone);
	    Counselor updatedCounselor = counsellorRepository.save(counselor);
	    
	    ResponseStructure<Long> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Phone number updated successfully");
	    response.setData(updatedCounselor.getPhone()); 

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCounsellor(Integer cid) {
	    Counselor counselor = counsellorRepository.findById(cid)
	            .orElseThrow(() -> new CounsellorNotFound("Counselor Not found"));

	    ResponseStructure<Counselor> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Counselor retrieved successfully");
	    response.setData(counselor); 

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteCounsellor(Integer cid) {
	    Counselor counselor = counsellorRepository.findById(cid)
	            .orElseThrow(() -> new CounsellorNotFound("Counselor Not found"));

	    counsellorRepository.delete(counselor);
	    
	    ResponseStructure<String> response = new ResponseStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Counselor deleted successfully");
	    response.setData("Counselor with ID " + cid + " has been deleted.");

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
