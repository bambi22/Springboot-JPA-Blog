package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Blogger;
import com.cos.blog.model.Board;

//자동으로 bean 등록
//어노테이션 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer>{


}