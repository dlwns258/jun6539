package com.bigdata2019.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
}
