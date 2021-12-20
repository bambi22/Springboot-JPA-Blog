package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Blogger;
import com.cos.blog.model.RoleType;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void join(Blogger user) {
		System.out.println(user.getPassword());
		String encPw = encoder.encode(user.getPassword());
		user.setPassword(encPw);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	/*
	 * @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료 (정합성
	 * 유지) public Blogger login(Blogger user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
