package com.treefactory.board.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.treefactory.board.service.BoardService;
import com.webjjang.util.PageObject;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	//의존성(Dependency)자동주입(Inject)
	@Autowired
	private BoardService service;
	
	//1. list
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception {

		// 알아서 jsp로 간다 // 페이지가 1보다 작으면 1페이지로 세팅해준다
		if(pageObject.getPage() < 1) pageObject.setPage(1);

		model.addAttribute("list", service.list(pageObject));
		log.info("게시판 리스트 처리");
		
		return "board/list";
	}

	
	//2. view
	@GetMapping("/view.do")
	// 변수이름과 key를 맞춰서 받는다
	public String view(Long no) throws Exception {

		log.info("게시판 글 보기 no: " + no);

		return "board/view";
	}
	
	//3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception {

		log.info("게시판 글쓰기 폼");

		return "board/write";
	}
	//3-2. write
	@PostMapping("/write.do")
	public String write() throws Exception {

		log.info("게시판 글쓰기 처리");
		// 같은 위치에있다 list.do 만 붙여준다
		return "redirect:list.do";
	}
	//4-1. updateForm
	@GetMapping("/update.do")
	public String updateForm(Long no) throws Exception {

		log.info("게시판 글 수정  폼 no: " + no);

		return "board/update";
	}
	//4-2. update
	@PostMapping("/update.do")
	public String update() throws Exception {

		log.info("게시판 글수정 처리 ");

		return "redirect:view.do?no=10";
	}
	//5. delete
	@GetMapping("/delete.do")
	public String delete(long no) throws Exception {

		log.info("게시판 글삭제 처리 no :" + no);

		return "redirect:list.do";
	}
}
