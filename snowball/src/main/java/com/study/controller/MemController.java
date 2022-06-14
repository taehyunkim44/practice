package com.study.controller;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.dto.Criteria;
import com.study.dto.MemDTO;
import com.study.dto.PageDTO;
import com.study.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mem/*")
public class MemController {
	
	@Autowired
	private MemService service;
	
	
	//  /board/list 컨트롤러 작성
	
	@GetMapping("/mem_list")
	public void list(Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("전체 리스트 요청 "+cri);
		
		List<MemDTO> list = service.getList(cri);
		//전체 게시물 개수
		int total = service.getTotalCnt(cri);
		
		model.addAttribute("pageDto", new PageDTO(cri, total));
		model.addAttribute("list", list);
	}

	@PostMapping("/mem_delete")
	public String listPost(String mem_id,RedirectAttributes rttr) {
		rttr.addAttribute("mem_id",mem_id);
		
		return"redirect:/mem/mem_remove";
		
	}
	
	// /board/register 컨트롤러 작성

	@GetMapping("/mem_register")
	public void register() {
		log.info("register 폼 요청");
	}
	
	// post
	@PostMapping("/mem_register")
	public String registerPost(MemDTO insertDto,Criteria cri, RedirectAttributes rttr) {
		log.info("글 등록 요청 "+insertDto);
		
		service.insert(insertDto);
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());	
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", insertDto.getMem_id());
		log.info("확인@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return "redirect:/mem/mem_list";
	}
	
	// /board/read + bno 
	// bno에 해당하는 게시물 읽어온 후 read.jsp 보여주기
	@GetMapping({"/mem_read","/mem_modify"})
	public void readGet(String mem_id,@ModelAttribute("cri") Criteria cri,Model model) {
		log.info("게시물 요청 "+mem_id);
		log.info("게시물 요청 "+cri);
		
		MemDTO dto = service.getRow(mem_id);
		model.addAttribute("dto", dto);
	}
	
	// /board/read + post => 수정 성공 시 수정된 게시물 보여주기
	@PostMapping("/mem_update")
	public String modify(MemDTO updateDto,@ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) {
		log.info("게시물 수정 요청 "+updateDto);
		log.info("게시물 수정 요청 - cri "+cri);
		
		service.update(updateDto);
		
		//수정 성공
		rttr.addAttribute("mem_id", updateDto.getMem_id());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());	
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		
		return "redirect:/mem/mem_read";
	}
	
	// /board/remove + bno
	// 성공시 list 보여주기
	@GetMapping("/mem_remove")
	public String remove(String mem_id,Criteria cri,RedirectAttributes rttr) {
		log.info("게시물 삭제 요청 "+mem_id);
		log.info("게시물 삭제 요청-cri "+cri);
		
		
		//DB작업 - 게시글 삭제 + 첨부파일 삭제 + 댓글 삭제
		service.delete(mem_id);
		
		// 주소줄에 딸려보내는 방식
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		// 세션을 이용하는 방식
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/mem/mem_list";
	}
	
	
	
	
	
	
}











