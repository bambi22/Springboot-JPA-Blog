package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Blogger;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired private BoardRepository boardRepository;
	@Autowired private ReplyRepository replyRepository;
	
	@Transactional
	public void save(Board board, Blogger user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> boardProc(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board boardView(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 해당 게시글을 찾을 수 없습니다.");
				});
	}

	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void update(int id, Board reqBoard) {
		Board board = boardRepository.findById(id) 
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패: 해당 게시글을 찾을 수 없습니다.");
				}); //영속화 완료
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
		//해당 함수 종료 시(Service 종료될 때) 트랜잭션 종료 -> 더티체킹 하면서 자동 업데이트, db flush
	}

	@Transactional
	public void replySave(ReplySaveRequestDto replySaveRequestDto) {
		int result = replyRepository.mSave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
//		Board board = boardRepository.findById(boardId).orElseThrow(()->{
//			return new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글을 찾을 수 없습니다.");
//		});
//		requestReply.setUser(user);
//		requestReply.setBoard(board);
//		replyRepository.save(requestReply);
	}
}
