package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor : final 멤버변수만 생성
public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	//장점: 매개변수 순서 상관X, 매개변수 갯수 별 생성자 필요X
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
		
	
}
