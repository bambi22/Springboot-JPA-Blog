package com.cos.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity	// 클래스를 테이블로 생성
public class Blogger {
	
	@Id	//Primary Key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BLOGGER_SEQ")
	@SequenceGenerator(name="BLOGGER_SEQ", sequenceName="USER_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username; //아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; //역할별 권한을 나눌 수 있음 (admin, user, manager), Enum을 쓰는 게 좋음 (도메인=범위를 설정할 수 있다.)
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
