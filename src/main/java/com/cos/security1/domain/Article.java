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


@Data
@Entity
@Getter
@Setter
public class Article {
	@Id
	@GeneratedValue
	private Long id; //(번호)
	
	//일련번호
	private String uniquenum;
	
	@Column
	private String papername; //(id_계약서 이름)
	private String orig_papername;//계약서 이름
	private int people_size; //(서명 인원) - 이 데이터로 for문 돌림
	private int sign_count;//사인완료한 인원 수
	
	private String file_path;//파일 저장한 경로
	private String ser_fileName;//서버에 저장된 파일 이름
	private String orig_name;//파일 원래 이름
	private long file_size;//파일 크기
	
	
	private String plain_text; //(평문) - 랜덤 생성
	
	private String people1_num; //(서명 1 IDCODE)
	private String people1_name; //(서명 1 이름) - 이름
	private String people1_sign; //(서명 1 서명 여부) - 처음에는 0 해당 서명자가 싸인하면 1
	private String people1_email; //(서명 1 이메일) - 메일
	private String people1_signname;
	private String people1_time;//서명한 시각
	
	
	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String people1_encrypt; //(서명 1 암호문) - 처음에는 NULL 서명자가 싸인하면 암호문 
	
	private String people2_num; // (서명 2 IDCODE)
	private String people2_name; //(서명 2 이름)
	private String people2_sign; //(서명 2 서명 여부)
	private String people2_email; //(서명 2 이메일)
	private String people2_signname;
	private String people2_time;
	
	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String people2_encrypt; //(서명 2 암호문)
	
	private String people3_num; // (서명 3 IDCODE)
	private String people3_name; //(서명 3 이름)
	private String people3_sign; //(서명 3 서명 여부)
	private String people3_email; //(서명 3 이메일)
	private String people3_signname;
	private String people3_time;
	
	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String people3_encrypt; //(서명 3 암호문)


	
	@Column
	private String x;
	
	@Column(columnDefinition = "TEXT",length=1000)//text 길이는 1000
	private String y;
	
	//등록한 시간
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column
	private int sign1_xpos;
	
	@Column
	private int sign1_ypos;
	
	@Column
	private int sign2_xpos;
	
	@Column
	private int sign2_ypos;
	
	@Column
	private int sign3_xpos;
	
	@Column
	private int sign3_ypos;

	@Column
	private String result_hash;

	public Article(Long id, String x, String y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Article() {
	}
}
