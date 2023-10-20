package edu.northeastern.info6250.RetroRides.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	  @RequestMapping(method=RequestMethod.GET)
	  public ModelAndView logout(HttpSession session) {
	    session.invalidate();
	    return new ModelAndView("login");
	  }
}
