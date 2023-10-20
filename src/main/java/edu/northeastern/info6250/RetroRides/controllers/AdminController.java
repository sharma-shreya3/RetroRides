package edu.northeastern.info6250.RetroRides.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.northeastern.info6250.RetroRides.dao.VehicleDAO;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	VehicleDAO vehicleDAO;
	
	@Autowired
	ServletContext servletContext;
	
	
	@GetMapping(value = "/admin/adminVehicleDetails.*")
	public ModelAndView loadVehicleDetailsPage(HttpServletRequest request) {
		System.out.println("inside admin vehicleDetails  controller");
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		String id = request.getParameter("VehicleId");
		if (id != null) {
			int vehicleID = Integer.parseInt(id);
			try {
				VehicleDetails vehicleDetails = vehicleDAO.getVehicleDetailByID(vehicleID); 
				session.setAttribute("vehicleDetail", vehicleDetails);
				return new ModelAndView("adminVehicleDetails", "user", u);
			} catch (UserException e) {
				e.printStackTrace();
				System.out.println("vehicle detail fetch by id not working");
				return new ModelAndView("error", "errorMessage", e);
			}
		
		} else {
			return new ModelAndView("error", "errorMessage", "Vehicle Details ID not found");
		}
	}
	
	@GetMapping(value = "/admin/updateVehicleDetails.*")
	public ModelAndView adminUpdateVehicleDetailsLoadPage(HttpServletRequest request, Model model) {
		System.out.println("inside update admin load page controller");
		HttpSession session = (HttpSession) request.getSession();
		VehicleDetails vd = (VehicleDetails) session.getAttribute("vehicleDetail");
		model.addAttribute("vehicleDetail", vd);
		User u = (User) session.getAttribute("user");
		return new ModelAndView("adminVehicleDetailsUpdate");
	}
	
	@PostMapping(value = "/admin/updateVehicleDetails")
	public ModelAndView adminUpdateVehicleDetails (HttpServletRequest request, HttpSession session,
			@ModelAttribute("vehicleDetail") VehicleDetails vehicleDetail, BindingResult bindingResult) {
		System.out.println ("inside update vehicleDetails post mapping in admin controller");
		String action = request.getParameter("action");
		int vehicleDetailID = Integer.parseInt(request.getParameter("vehicleDetailID"));
		System.out.println("vehicleDetailID submitted: " + vehicleDetailID);
			try {
				int updatedRows = vehicleDAO.updateVehicleDetailStatusActiveIncative(vehicleDetail, vehicleDetailID);
				return new ModelAndView ("vehicleUpdateSuccess");
			} catch (UserException e) {
				System.out.println("unable to update vehicle details");
				return new ModelAndView ("error", "errorMessage", e);
			}
	}

}
