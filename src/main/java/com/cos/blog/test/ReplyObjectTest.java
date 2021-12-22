package com.cos.blog.test;

import org.junit.jupiter.api.Test;
import com.cos.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void ReplyTest() {
		Reply reply = Reply.builder()
				.id(1)
				.user(null)
				.board(null)
				.content("안녕")
				.build();
				
	// 오브젝트 출력하게 되면 자동으로 toString() 호출
		System.out.println(reply);
	}
}
