package edu.northeastern.info6250.RetroRides.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.northeastern.info6250.RetroRides.dao.BidDAO;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.BidDetails;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BidController {
	
	@Autowired
	BidDAO bidDAO;
	
	
	@GetMapping(value = "/user/uploadBidDetails.*")
	public ModelAndView viewUploadBidDetails(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside vehicle details post controller");
		User u = (User) session.getAttribute("user");
		VehicleDetails vehicleDetail = (VehicleDetails) session.getAttribute("vehicleDetail");
		try {
			int vehicleId = vehicleDetail.getVehicleID();
			int userID = u.getUserId();
			BidDetails bidDetail = bidDAO.getBidDetailsOnUserVehicle(vehicleDetail, u);
			if (bidDetail == null) {
				return new ModelAndView("uploadBidDetails", "bidDetail", new BidDetails());
			} else {
				System.out.println("bidDetail is not null: " + bidDetail.getVehicleDetail());
				return new ModelAndView("uploadBidDetails", "bidDetail", bidDetail);
			}
		} catch (UserException e) {
			e.printStackTrace();
			System.out.println("Unable to fetch bid details");
			return new ModelAndView("error", "errorMessage", e);
		}	
	}
	
	
	@PostMapping(value = "/user/uploadBidDetails")
	public ModelAndView userUploadBidDetails (HttpServletRequest request, HttpSession session,
			@ModelAttribute("bidDetail") BidDetails bidDetail, BindingResult bindingResult) {
		String action = request.getParameter("action");
		System.out.println("inside vehicle details post controller");
		User u = (User) session.getAttribute("user");
		VehicleDetails vehicleDetail = (VehicleDetails) session.getAttribute("vehicleDetail");
	
		System.out.println("inside bid controller: upload bid controller");
		System.out.println("action: " + action);
		if (u == null || vehicleDetail == null) {
			System.out.println("email/Password does not exist");
			return new ModelAndView("error", "errorMessage", "Logged in User Details not found"); 
		} 
		if (action.equals("Place Bid")) {
			bidDetail.setUser(u);
			bidDetail.setVehicleDetail(vehicleDetail);
			try {
				BidDetails bd = bidDAO.insertBidDetails(bidDetail);
				if (bd != null) {
					ModelAndView mv = new ModelAndView();
					mv.addObject("user", u);
					mv.addObject("successMessage", "Bid Placed Successfully");
					mv.setViewName("bidSuccess");
					return mv;
				} else {
					System.out.println("Unable to fetch bid details");
					return new ModelAndView("error", "errorMessage", "unable to add bid");
				}
			} catch (UserException e) {
				e.printStackTrace();
				System.out.println("unable to add bid");
				return new ModelAndView("error", "errorMessage", e);
			}
		} else if (action.equals("Update Bid")) {
			bidDetail.setUser(u);
			bidDetail.setVehicleDetail(vehicleDetail);
			try {
				int updatedRows = bidDAO.updateBidDetails(bidDetail);
				if (updatedRows > 0) {
					ModelAndView mv = new ModelAndView();
					mv.addObject("user", u);
					mv.addObject("successMessage", "Bid Updated Successfully");
					mv.setViewName("bidSuccess");
					return mv;
				} else {
					System.out.println("Unable to update bid details");
					return new ModelAndView("error", "errorMessage", "unable to update bid");
				}
			} catch (UserException e) {
				e.printStackTrace();
				System.out.println("unable to update bid");
				return new ModelAndView("error", "errorMessage", e);
			}
		} else {
			return null;
		}
		
	 }
	
	@GetMapping(value = "/user/viewBidDetails.*")
	public ModelAndView viewBidDetails(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside vehicle details post controller");
		VehicleDetails vd = (VehicleDetails) session.getAttribute("vehicleDetail");
		try {
			List<BidDetails> bidDetailList = bidDAO.viewBideDetails(vd);
			if (bidDetailList.size() > 0) {
				return new ModelAndView("viewBidDetails", "bidDetailList", bidDetailList);
			} else {
				return new ModelAndView("vehicleBidListingNoResult", "bidDetail", bidDetailList);
			}
		} catch (UserException e) {
			e.printStackTrace();
			System.out.println("Unable to fetch bid details");
			return new ModelAndView("error", "errorMessage", e);
		}	
	}
	
	@GetMapping(value = "/user/bidListing.*")
	public ModelAndView bidListingUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside bid lisitng per user get controller");
		User u = (User) session.getAttribute("user");
		try {
			List<BidDetails> bidDetailsList = bidDAO.fetchUserBidListing(u);
			if (bidDetailsList.size() > 0) {
				return new ModelAndView("viewBidDetails", "bidDetailList", bidDetailsList);
			} else {
				return new ModelAndView("bidListingNoResult", "bidDetail", bidDetailsList);
			}
		} catch (UserException e) {
			e.printStackTrace();
			System.out.println("Unable to fetch bid details for this user");
			return new ModelAndView("error", "errorMessage", e);
		}
	}
	
	
}
