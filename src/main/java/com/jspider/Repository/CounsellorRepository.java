package com.jspider.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jspider.Entity.Counselor;

public interface CounsellorRepository extends JpaRepository<Counselor, Integer>{

	Optional<Counselor> findByEmail(String email);
}
