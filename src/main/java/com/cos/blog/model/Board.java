package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
	@Id	//Primary Key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_SEQ_GENERATOR")
	@SequenceGenerator(name="BOARD_SEQ_GENERATOR", sequenceName="BOARD_SEQ", initialValue=1, allocationSize=1)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //썸머노트 라이브러리 <html>태그가 섞여서 디자인 됨.
	
	@ColumnDefault("0")
	private int count;		//조회수
	
	@ManyToOne		// Many = Board, One = Blogger
	@JoinColumn(name="userId")
	private Blogger user;
	// DB는 오브젝트를 저장할 수 없음 - FK 사용, 자바는 오브젝트를 저장할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
