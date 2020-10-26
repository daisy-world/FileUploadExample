package com.example.FileUploadExample.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.FileUploadExample.entity.Document;
import com.example.FileUploadExample.service.DocumentService;

@Controller
public class DocumentController {
	
	@Autowired
	DocumentService documentService;

	
	@PostMapping("/saveFile")
	public String uploadFile(@RequestParam("file") MultipartFile file,ModelMap map ) { 
	    String message = "";
	    String 	documentId ="";           

		if (file.isEmpty()) {
	 	      message = "Could not upload the file!!";
		}else {
			
		 	documentId = documentService.saveUploadedFile(file);
	    message = "Uploaded the file successfully: " + file.getOriginalFilename();
			
		}
		
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/downloadFile/")
					.path(documentId)
					.toUriString();
		    
		    map.put("message", message);
		    map.put("downloadurl", fileDownloadUri);
			return "success";
			
			
	}
	
	
   @GetMapping("/downloadFile/{documentId}")
	public void downloadFile(@PathVariable("documentId") String documentId, HttpServletResponse response)
			throws IOException {

		Document document = documentService.getDocument(documentId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + document.getDocument_name() + "\"");
			response.setContentType(document.getDocument_type());


			InputStream ins = new ByteArrayInputStream(document.getFile());
			IOUtils.copy(ins, response.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
