package com.cos.security1.domain;

//계약서 생성에서 가져올 데이터들

import lombok.Getter;
import lombok.Setter;

//getter setter가 있어야 스프링이 자동으로 연결해줌
@Getter
@Setter
public class MessageForm {
	
	@Override
	public String toString() {
		return "MessageForm [title=" + title + ", name1=" + name1 + ", email1=" + email1 + ", name2=" + name2
				+ ", email2=" + email2 + ", name3=" + name3 + ", email3=" + email3 + ", signNum=" + signNum + "]";
	}


	private String title;
	
	private String name1;
	private String email1;
	
	private String name2;
	private String email2;
	
	private String name3;
	private String email3;
	
	//private long z;
	
	private int signNum;

	private String per1[];
	private String per2[];
	private String per3[];

	public Article toEntity() {
		// TODO Auto-generated method stub
		Article newArticle = new Article();
		
		newArticle.setPapername(title);
		newArticle.setOrig_papername(title);
		newArticle.setPeople1_name(name1);
		newArticle.setPeople1_email(email1);
		
		newArticle.setPeople2_name(name2);
		newArticle.setPeople2_email(email2);
		
		newArticle.setPeople3_name(name3);
		newArticle.setPeople3_email(email3);
		
		newArticle.setPeople_size(signNum);
		
		return newArticle;
	}
	
	public Copyright toCopyright() {
		Copyright copyright = new Copyright();
		
		copyright.setPapername(title);
		copyright.setOrig_papername(title);
		copyright.setPeople1_name(name1);
		copyright.setPeople1_email(email1);

		
		copyright.setPeople_size(signNum);
		
		return copyright;
	}
}
