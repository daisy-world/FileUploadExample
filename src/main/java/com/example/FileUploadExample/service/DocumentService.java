package com.example.FileUploadExample.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadExample.entity.Document;
import com.example.FileUploadExample.entity.DocumentDetails;

public interface DocumentService {


	

	public String saveUploadedFile(MultipartFile file);
	
	public Document getDocument(String  fileName);

	
	public DocumentDetails storeFile(MultipartFile file, String userId, String orgId, String docCatagory);
	
	public DocumentDetails getDocumentById(String docId);

}
