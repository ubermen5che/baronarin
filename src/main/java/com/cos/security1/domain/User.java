package com.cos.security1.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
public class User {

	@Id @Column(name = "email")
	private String email;//username이 이메일
	private String realName;
	private String password;
	private String phoneNumber;
	private String authKey;
	private String authStatus;
	private String role;//ROLE_USER, ROLE_ADMIN
	private int signCount;
	//private Timestamp loginDate;//로그인 할 때 마다 갱신

	private String signName;//유저 사인 이미지이름

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserSign> userSigns = new ArrayList<>();

	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String publicKey;

	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String privateKey;

	private String provider;//google login
	private String providerId;//google-sub
	
	@CreationTimestamp
	private Timestamp createDate;

	public void addUserSign(UserSign userSign) {
		this.userSigns.add(userSign);
		if (userSign.getUser() != this) {
			userSign.setUser(this);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder
				.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}