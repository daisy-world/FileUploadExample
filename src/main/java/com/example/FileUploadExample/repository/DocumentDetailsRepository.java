package com.example.FileUploadExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FileUploadExample.entity.DocumentDetails;


@Repository
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, String> {
	
	
	

}
