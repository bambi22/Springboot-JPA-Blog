package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Blogger;

//자동으로 bean 등록
//어노테이션 생략 가능
public interface UserRepository extends JpaRepository<Blogger, Integer>{

}

// JPA Naming 전략
// findByUsernameAndPassword
// SELECT * FROM blogger WHERE username = ? AND password = ?;
//Blogger findByUsernameAndPassword(String username, String password);

//@Query(value="SELECT * FROM blogger WHERE username = ?1 AND password = ?2", nativeQuery = true)
//Blogger login(String username, String password);
