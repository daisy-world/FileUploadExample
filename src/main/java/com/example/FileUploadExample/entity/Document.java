package com.example.FileUploadExample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="document")
public class Document {
	
	

	@Id
	@GenericGenerator(name = "document_id", strategy = "com.example.FileUploadExample.generator.DocumentGenerator")
	@GeneratedValue(generator = "document_id")
	
	@Column(name="document_id")
	private String document_id;

	@Column(name="document_name")
	private String document_name;
	
	@Column(name="document_type")
	private String document_type;

	    @Column(name="document_data")
	    @Lob
	    private byte[] file;

	



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

		public String getDocument_type() {
			return document_type;
		}

		public void setDocument_type(String document_type) {
			this.document_type = document_type;
		}

		public byte[] getFile() {
			return file;
		}

		public void setFile(byte[] file) {
			this.file = file;
		}

	
	    
	    

}
