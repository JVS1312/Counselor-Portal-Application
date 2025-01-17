package com.jspider.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.Entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer>{
	Optional<Enquiry> findByEmail(String email);

	


}
