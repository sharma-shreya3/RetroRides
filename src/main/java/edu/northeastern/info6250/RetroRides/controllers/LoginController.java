package edu.northeastern.info6250.RetroRides.controllers;

import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import edu.northeastern.info6250.RetroRides.dao.UserDAO;
import edu.northeastern.info6250.RetroRides.dao.VehicleDAO;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.validators.UserValidator;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
public class LoginController {
	@Autowired
	UserDAO userDao;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	VehicleDAO vehicleDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping(value = "/user/login.*")
	public ModelAndView home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return new ModelAndView("login");
	}
	
	@PostMapping(value = "user/login")
	public ModelAndView loginUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
	
		System.out.println("Inside the user controller");
		try {
				System.out.print("loginUser");
				
				User u = userDao.getLoggedInUser(request.getParameter("email"), request.getParameter("password"));
				
					
				System.out.println("user" + u);
				System.out.println("firstName"+u.getFirstName());
				
				
				if(u == null){
					System.out.println("Email/Password does not exist");
					return new ModelAndView("login", "errorMessage", "email/Password is incorrect or does not exist");
				}
				
				
				else if(u.getRole().equals("User"))
				{	
					ModelAndView mv=new ModelAndView();
					List<VehicleDetails> vdList = vehicleDAO.getAllVehicles();
					mv.addObject("vehicleList",vdList);
					mv.addObject("user",u);
					session.setAttribute("user", u);
					mv.setViewName("userHome");
				return mv;
				}

				else if(u.getRole().equals("ADMIN"))
				{
					ModelAndView mv=new ModelAndView();
					List<VehicleDetails> vdList = vehicleDAO.getAllUpdatedAddedVehicles();
					System.out.println("lsit in controller: " + vdList.size());
					mv.addObject("vehicleList",vdList);
					mv.addObject("user",u);
					session.setAttribute("user", u);
					mv.setViewName("adminHome");
					return mv;
							
				}
				
				
				
			}
			catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login" + e);
			}

			return null;
		}
	
	// user home page from session
	@GetMapping(value = "user/login")
	public ModelAndView loggedinUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		
		System.out.println("Inside the user controller");
		
		try {
				System.out.print("loginUser");
				User u = (User) session.getAttribute("user");
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				return new ModelAndView("error", "errorMessage", "error while retrieving session");
			}
			
			else if(u.getRole().equals("User"))
			{			
				ModelAndView mv=new ModelAndView();
				List<VehicleDetails> vdList = vehicleDAO.getAllVehicles();
				mv.addObject("vehicleList",vdList);
				mv.addObject("user",u);
				session.setAttribute("user", u);
				mv.setViewName("userHome");
				return mv;
			}

			else if(u.getRole().equals("ADMIN"))
			{
				ModelAndView mv=new ModelAndView();
				List<VehicleDetails> vdList = vehicleDAO.getAllUpdatedAddedVehicles();
				mv.addObject("vehicleList",vdList);
				mv.addObject("user",u);
				session.setAttribute("user", u);
				mv.setViewName("adminHome");
				return mv;
						
			}
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login" + e);
		}

		return null;
	}
}
