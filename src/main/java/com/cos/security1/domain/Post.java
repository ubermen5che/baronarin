package com.cos.security1.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

//자료실
@Data
@Entity
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue
	private Long id; //(번호)
	
	@Column
	private String postType;
	
	@Column
	private String specificType;

	@CreationTimestamp
	private Timestamp createDate;
	
	@Column
	private String title;
	
	@Column
	private String serverpdf;
	
	@Column
	private String serverhwp;

	@Column
	private String pdfLink;

	@Column
	private String hwpLink;

	public Post(String postType, String specificType, String title, String serverpdf, String serverhwp,     String pdfLink, String hwpLink) {
		super();
		this.postType = postType;
		this.specificType = specificType;
		this.title = title;
		this.serverpdf = serverpdf;
		this.serverhwp = serverhwp;
		this.pdfLink = pdfLink;
		this.hwpLink = hwpLink;
	}

	public Post()
	{
		
	}
}
