package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

//자동으로 bean 등록
//어노테이션 생략 가능
public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying
	@Query(value="INSERT INTO reply(id, userId, boardId, content, createDate) VALUES(REPLY_SEQ.nextval, ?1, ?2, ?3, SYSDATE)", nativeQuery = true)
	int mSave(int userId, int boardId, String content); 

}