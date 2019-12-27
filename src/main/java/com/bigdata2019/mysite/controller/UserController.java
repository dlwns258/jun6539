package com.bigdata2019.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigdata2019.mysite.service.UserService;
import com.bigdata2019.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String join(UserVo vo) {
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value = "email",required = true,defaultValue = "") String email, 
			@RequestParam(value = "password",required = true,defaultValue = "") String password) {
		UserVo uservo = userService.getUser(email, password);
		if(uservo == null) {
			return "redirect:/user/login?result=fail";
		}
		
		session.setAttribute("authUser", uservo);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser != null) {
			session.removeAttribute("authUser");
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/";
		}
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVo vo,HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		Long no = authUser.getNo();
		System.out.println(vo.getGender());
		userService.userUpdate(no, vo);
		
		return "redirect:/";
	}
}
