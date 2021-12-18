package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자 요청 ->응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1, "ssar", "123", "email");
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter:"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter:"+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청 후 get 요청만 가능
	@GetMapping("/http/get")
	public String getTest(Member m) { //id=1&username=ssar&password=123&email=ssar@nate.com
		return "get 요청" + m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter (스트링부트가 매핑)
		//x-www-form-unlencoded : form 태그의 input 데이터 -> Member m
		//raw 데이터 : string -> @RequestBody String text
		//application/json : {"id" : 1, "username" : "ssar", "password" : 123} -> @RequestBody Member m
		return "post 요청" + m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) { // 요청 데이터 외의 멤버변수는 null
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
