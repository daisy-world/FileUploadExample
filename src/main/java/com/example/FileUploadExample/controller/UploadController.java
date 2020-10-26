package com.example.FileUploadExample.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.FileUploadExample.entity.DocumentDetails;
import com.example.FileUploadExample.service.DocumentService;

@Controller
public class UploadController {
	
	
	@Autowired
	DocumentService documentService;

	@GetMapping("/")
	public String showIndex() {
		
		
		return "index" ;
	}
	
	@PostMapping("/uploadFile")
	 public String uploadFile(@RequestParam("file") MultipartFile file,ModelMap map) {
	    String message = "";
	    String docId=null;
	    if(file.getSize()>0) {
	    	 try {
	 	    	String orgId  ="ORG00001";
	    		 String userId ="USER00002";

	 	    	String docCatagory ="education";
	 	    	
	 	   	DocumentDetails documentDetails =  	documentService.storeFile(file, userId, orgId, docCatagory);
	 	    docId= documentDetails.getDocument_id()	;
	 	   	message = "Uploaded the file successfully: " + file.getOriginalFilename();
	 	    } catch (Exception e) {
	 	    	e.printStackTrace();
	 	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	 	   }	
	    }
	    
	  if(null!=docId && !docId.isEmpty()) {
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/viewFile/")
					.path(docId)
					.toUriString();  
		    map.put("downloadurl", fileDownloadUri);

	  }
	    
	    
	    map.put("message", message);
		return "success";
	
		
}
	
	@GetMapping("/viewFile/{docId}")
	public ResponseEntity<Resource> viewFile(@PathVariable String docId,HttpServletResponse response) throws IOException {
		
	  	DocumentDetails documentDetails = documentService.getDocumentById(docId);
	  	
	  	String folderPath = documentDetails.getPath();
	  	String fileName = documentDetails.getDocument_name();
	  	
  		response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
	 
	  		 InputStreamResource resource = new InputStreamResource(new FileInputStream(folderPath));

	  	    return ResponseEntity.ok()
	  	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	  	            .body(resource);
	  	    
	  	    
	  	
	}
	
	
	
	 @GetMapping("/downloadZip")
	    public void downloadFile(HttpServletResponse response) {
		 
		 
		 
	 }
	 }
	
	
	

