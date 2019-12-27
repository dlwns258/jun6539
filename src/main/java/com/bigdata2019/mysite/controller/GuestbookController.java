package com.bigdata2019.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigdata2019.mysite.repository.GuestbookRepository;
import com.bigdata2019.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookRepository guestbookRepository;

	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public String insert(GuestBookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String deleteform() {
		return"guestbook/delete";
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public String delete(@RequestParam(value="no",required = true,defaultValue = "0") Long no, 
						 @RequestParam(value="password",required = true,defaultValue = "") String password) {
		guestbookRepository.delete(no, password);
		return "redirect:/guestbook";
	}
}
