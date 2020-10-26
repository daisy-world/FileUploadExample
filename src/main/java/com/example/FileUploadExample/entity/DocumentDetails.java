package com.example.FileUploadExample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="document_details")
public class DocumentDetails {
	

	@Id
	@GenericGenerator(name = "document_id", strategy = "com.example.FileUploadExample.generator.DocumentGenerator")
	@GeneratedValue(generator = "document_id")
	
	@Column(name="document_id")
	private String document_id;
	
	@Column(name="document_name")
	private String document_name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="path")
	private String path;

	public String getDocument_id() {
		return document_id;
	}

	public void setDocument_id(String document_id) {
		this.document_id = document_id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
