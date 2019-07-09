package com.rab3.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rab3.dtos.LoginDto;
import com.rab3.services.LoginService;

@Controller
public class LoginController {
	
	private LoginService loginService;

	@RequestMapping(value="login", method= RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("i am being called from login");
		LoginDto loginDto = new LoginDto();
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("loginObj", loginDto);
		return mv;
	}
	
	@RequestMapping(value="login-process", method= RequestMethod.POST)
	public ModelAndView processLogin(LoginDto loginDto) {
		boolean loginValid = loginService.isLoginValid(loginDto.getUsername(), loginDto.getPassword());
		ModelAndView mv = new ModelAndView();
		if(loginValid) {
			mv.setViewName("home");
		} else {
			mv.setViewName("login");
			mv.addObject("loginObj", loginDto);		
			mv.addObject("message", "Your username/password doesnt match.");
		}
		return mv;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}
