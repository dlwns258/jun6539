package com.bigdata2019.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("")
	public String index() {
		return "/board/list";
	}
	@RequestMapping("/newbook")
	public String newbook() {
		return "/board/write";
	}
}
