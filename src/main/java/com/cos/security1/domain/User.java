package com.cos.security1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;//No identifier specified for entity문제 import가 잘못됨 

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//기본 varchar(255)
	private int id;
	private String realName;
	private String email;//username이 이메일
	private String password;
	private String phoneNumber;
	private String authKey;
	private String authStatus;
	//private String email;
	private String role;//ROLE_USER, ROLE_ADMIN
	//private Timestamp loginDate;//로그인 할 때 마다 갱신
	private String signName;//유저 사인 이미지이름
	
	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String publicKey;

	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String privateKey;

	private String provider;//google login
	private String providerId;//google-sub
	
	@CreationTimestamp
	private Timestamp createDate;
}
