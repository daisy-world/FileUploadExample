package com.example.FileUploadExample.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.FileUploadExample.entity.Document;
import com.example.FileUploadExample.entity.DocumentDetails;
import com.example.FileUploadExample.repository.DocumentDetailsRepository;
import com.example.FileUploadExample.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentDetailsRepository documentDetailsRepository;
	
	@Autowired
	
	DocumentRepository documentRepository;
	
	
	@Value("${data.filepath}")
	private String filepath;
	
		
	
	

	@Override
	public String saveUploadedFile(MultipartFile file) {
		
		String docId = null;

		if (!file.isEmpty()) {
			
			Document document = new Document();
			document.setDocument_name(file.getOriginalFilename());
			document.setDocument_type(file.getContentType());
			try {
				document.setFile(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Document doc = 	documentRepository.save(document);
			docId = doc.getDocument_id();
			
		}
		return docId;
	}
	
	
	
	

	@Override
	public Document getDocument(String  docId) {

		Document document = documentRepository.findById(docId).get();
		
		return document;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@Override
	public DocumentDetails storeFile(MultipartFile file, String userId, String orgId, String docCatagory) {

	
		String filePathForDb = ""; 
		DocumentDetails documentDetails =null;
		
		String stringfolderPath = generateFolderPath(orgId, userId, docCatagory);
		Path folderPath = Paths.get(stringfolderPath);

		try {
			
			if (!folderExists(stringfolderPath)) {
				createFolders(stringfolderPath);
			}
			Files.copy(file.getInputStream(),folderPath.resolve(file.getOriginalFilename()));
			
			filePathForDb = stringfolderPath.concat(File.separator).concat(file.getOriginalFilename());
			
			System.out.println("Data :"+filePathForDb);
			
			//insert to document table //
			DocumentDetails document = new DocumentDetails();
			document.setDocument_name(file.getOriginalFilename());
			document.setPath(filePathForDb);
			document.setType(file.getContentType());
			documentDetails=	documentDetailsRepository.save(document);
			
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
		return documentDetails;

	}
	
	public String generateFolderPath(String orgId, String userId, String docCatagory) {

		String path =filepath
					.concat(File.separator).concat(orgId)
					.concat(File.separator).concat(userId)
					.concat(File.separator).concat(docCatagory);

		return path;
	}
	
	public boolean createFolders(String path) {
		return new File(path).mkdirs();
	}
	
	public boolean folderExists(String path) {
		File directory = new File(path);
		return directory.exists();
	}

	@Override
	public DocumentDetails getDocumentById(String docId) {
		DocumentDetails docs = null;
		Optional<DocumentDetails> documentDetails = documentDetailsRepository.findById(docId);
		
		if(documentDetails.isPresent()) {
			
			 docs = documentDetails.get();
		}
		
		return docs;
	}
	
	
	
	

}
