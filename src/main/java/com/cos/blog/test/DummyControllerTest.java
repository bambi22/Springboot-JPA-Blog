package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Blogger;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "해당 id는 없는 id입니다.";
		}
	return "삭제되었습니다. id : " +id;
	}
	
	// save함수는 id를 전달하지 않으면 insert 해주고
	// id 전달하면 id에 대한 데이터가 있으면 update
	// id 전달하면 id에 대한 데이터가 없으면 insert
	// email, password 수정 -> json으로 수정
	@Transactional	// 컨트롤러 종료 시 commit
	@PutMapping("/dummy/user/{id}")
	public Blogger updateUser(@PathVariable int id, @RequestBody Blogger requestUser) { // json 데이터로 요청 -> java Obgect(Message Converter의 Jackson라이브러리가 변환)
		System.out.println("id : " +id);
		System.out.println("password : " +requestUser.getPassword());
		System.out.println("email : "+ requestUser.getEmail());
		
		Blogger user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다..");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		// 더티 체킹 - 변경 감지7
		
		return user;
	}
	
	@GetMapping("dummy/users")
	public List<Blogger> list(){
		return userRepository.findAll();
	}
	
	// 페이징 ?page=0 부터 시작
	// 한페이지당 2개씩
	@GetMapping("dummy/user")
	public List<Blogger> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<Blogger> pagingUser = userRepository.findAll(pageable);
		// if(pagingUser.isLast())		분기 처리 가능
		List<Blogger> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8082/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public Blogger detail(@PathVariable int id) {
		Blogger user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자는 없습니다. id : " +id);
			}
		});
		// 요청 : 웹브라우저 html, js
		// Blogger 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있게) -> json, 기존 스프링 (Gson 라이브러리 이용)
		// 스프링부트 = 메세지 컨버터 응답시 자동 작동
		// 자바 오브젝트 리턴 시 Jackson 라이브러리를 호출해서 json으로 변환시켜줌
		return user;
	}
	//람다식
	/* 
	 * Blogger user = userRepository.findById(id).orElseThrow(()->{
	 * return new IllegalArgumentException("해당 사용자는 없습니다.");
	 * });
	*/
	
	/*
	 *  //DB에서 데이터를 못 찾으면 null로 문제 발생할 수 있어 Optional로 객체를 감싸서 가져와서 null인지 판단해서 return
	 * Blogger user = userRepository.findById(id).orElseGet(
	 * new Supplier<Blogger>() { // null이면 대체
	 * @Override public Blogger get() { // 빈 객체 넣기 return new Blogger(); } });
	 */
	
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(Blogger u) { //변수명 정확히 쓰면 @RequestParam("username") 사용안해도 됨. key=value
		System.out.println("username"+u.getUsername());
		System.out.println("password"+u.getPassword());
		System.out.println("email"+u.getEmail());
		u.setRole(RoleType.USER);
		
		userRepository.save(u);
		return "회원가입 완료";
	}
}
