package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/approval/*")
public class IndexController {
	@GetMapping("/list")
	public void getList() {
		
	}
	@GetMapping("/create")
	public void getCreate() {
		
	}
	@PostMapping("/create")
	public String postCreate() {
		return "s";
	}
	
}
