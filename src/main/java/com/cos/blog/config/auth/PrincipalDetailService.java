package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.Blogger;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때 username, password 변수 가로채서 password 부분 처리는 알아서 함.
	// username이 DB에 있는지만 확인해주면 됨.
	// 로그인 시 실행됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Blogger principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." +username);
				});
		return new PrincipalDetail(principal); 	// 시큐리티 세션에 유저 정보가 저장됨.
	}

}
