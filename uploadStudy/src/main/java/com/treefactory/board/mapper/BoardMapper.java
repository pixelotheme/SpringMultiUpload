package com.treefactory.board.mapper;

import java.util.List;

import com.treefactory.board.vo.BoardVO;
import com.webjjang.util.PageObject;

public interface BoardMapper {

	//dao에서 작성한 메서드 형식으로 만들어준다
	
	//1-1. 리스트
	public List<BoardVO> list(PageObject pageObject) throws Exception;
	//1-2. 전체 데이터 개수
	public long getTotalRow(PageObject pageObject) throws Exception;
	//2-1. 보기
	public BoardVO view(long no) throws Exception;
	//2-2. 조회수 1증가
	public int increase(long no) throws Exception;
	//3. 글쓰기
	public int write(BoardVO vo) throws Exception;
	//4. 수정
	public int update(BoardVO vo) throws Exception;
	
	//5. 삭제
	public int delete(long no) throws Exception;
	
	
}
