package com.example.FileUploadExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.FileUploadExample.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, String>{
	
	
}
