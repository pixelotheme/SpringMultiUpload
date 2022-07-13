package com.treefactory.board.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.treefactory.board.mapper.BoardMapper;
import com.treefactory.board.vo.BoardVO;
import com.webjjang.util.PageObject;

import lombok.Setter;

@Service
public class BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardService.class);
	
	@Setter
	@Inject
	private BoardMapper mapper;
	
	public List<BoardVO> list(PageObject pageObject) throws Exception{
		
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("boardSerivce.list().pageObject -_" + pageObject);
		return mapper.list(pageObject);
	}
}
