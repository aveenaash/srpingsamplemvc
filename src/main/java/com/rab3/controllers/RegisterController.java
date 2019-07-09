package com.rab3.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rab3.dtos.LoginDto;
import com.rab3.dtos.SignUpDto;
import com.rab3.services.RegisterService;

@Controller
public class RegisterController {
	
	private RegisterService rs;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		mv.addObject("registerObject", new SignUpDto());
		return mv;
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView processRegister(SignUpDto signUpDto) {
		rs.register(signUpDto);
		LoginDto loginDto = new LoginDto();
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("loginObj", loginDto);
		return mv;
	}

	public RegisterService getRs() {
		return rs;
	}

	public void setRs(RegisterService rs) {
		this.rs = rs;
	}

}
