package com.cos.security1.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


//문의사항
@Data
@Entity
@Getter
@Setter

public class CustomerCenter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //(번호)

	@Column
	private String title;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(columnDefinition = "TEXT",length=1000)
	private String textbody;
	
	@Column
	private String username;
	
	@Column
	private String realname;
	
	@Column
	private String hierachyUrl;
	
	
	@Column
	private String secretcheck;
	
	@Column
	private String secretpassword;
	
	@Column
	private String filename;
	
	@Column
	private String server_filename;
	
	@Column(columnDefinition = "TEXT",length=1000)
	private String admincomment;
	
	public CustomerCenter(String title, String textbody, String username, String realname, String hierachyUrl, String secretcheck, String secretpassword, String filename, String server_filename) {

		this.title = title;
		this.textbody = textbody;
		this.username = username;
		this.realname = realname;
		this.hierachyUrl = hierachyUrl;
		this.secretcheck = secretcheck;
		this.secretpassword = secretpassword;
		this.filename = filename;
		this.server_filename = server_filename;
	}

	public CustomerCenter() {

	}
}
