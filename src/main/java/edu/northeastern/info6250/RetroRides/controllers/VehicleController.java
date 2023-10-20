package edu.northeastern.info6250.RetroRides.controllers;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.northeastern.info6250.RetroRides.dao.VehicleDAO;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.BidDetails;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class VehicleController{
	
	@Autowired
	VehicleDAO vehicleDAO;
	
	@Autowired
	ServletContext servletContext;
	
	
	@GetMapping(value = "/user/uploadVehicleDetails.*")
	public ModelAndView viewUploadVehicleDetails() {			
		return new ModelAndView("uploadVehicleDetails", "vehicle", new VehicleDetails());
	}
	
	@PostMapping(value = "/user/uploadVehicleDetails")
	public ModelAndView saveVehicleDetails(HttpServletRequest request,  @ModelAttribute("vehicle")VehicleDetails vehicleDetail , 
			BindingResult result) {	
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside vehicle details post controller");
		User u = (User) session.getAttribute("user");
		try {
			if (u == null) {
				System.out.println("email/Password does not exist");
				return new ModelAndView("error", "errorMessage", "Logged in User Details not found");
			} else {
				System.out.println("logged in User" + u);
				vehicleDetail.setUser(u);
				vehicleDetail.setStatus("ADDED");
				vehicleDetail.setFileName(vehicleDetail.getPhoto().getOriginalFilename());
				System.out.println("File Name: " + vehicleDetail.getPhoto().getOriginalFilename());
				System.out.println("File Name: " + vehicleDetail.getPhoto());
				VehicleDetails vd = vehicleDAO.uploadVehicleDetails(vehicleDetail);
				if (vd != null) {
					if (vd.getFileName().trim() != "" || vd.getFileName() != null ) {
						File directory;
						String check = File.separator;
						
						String path = null;
						if (check.equalsIgnoreCase("\\")) {
							path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																						  // so we need to replace build in the path
						}

						if (check.equalsIgnoreCase("/")) {
							path = servletContext.getRealPath("").replace("build/", "");
							path += "/"; // Adding trailing slash for Mac systems.
						}
						
						directory = new File(path + "/images/");
						System.out.println("File is stored at" + directory);
						boolean temp = directory.exists();
						if (!temp) {
							temp = directory.mkdir();
						}
						if (temp) {
							// We need to transfer to a file
							MultipartFile photoInMemory = vd.getPhoto();

							String fileName = photoInMemory.getOriginalFilename();
							// could generate file names as well

							File localFile = new File(directory.getPath(), fileName);

							// move the file from memory to the file

							try {
								photoInMemory.transferTo(localFile);
							} catch (IllegalStateException | IOException e) {
								System.out.println("*** IllegalStateException: " + e.getMessage());
					            return new ModelAndView("error", "errorMessage", e);
							}
							System.out.println("File is stored at" + localFile.getPath());

						} else {
							System.out.println("Failed to create directory!");
						}
					}
				} else {
					System.out.println("Problem occured in creating vehicle details");
					return new ModelAndView("error", "errorMessage", "Vehicle Details not entered Properly");
				}
				session.setAttribute("user", u);
				session.setAttribute("successMessage", "Vehicle Uploaded Successfully.");
				return new ModelAndView("vehicleDetails", "vehicleDetail", vd);
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("email/Password does not exist");
			return new ModelAndView("error", "errorMessage", e);
		}
		
	}
	
	@GetMapping(value = "/user/updateVehicleDetails.*")
	public ModelAndView userUpdateVehicleDetailsLoadPage(HttpServletRequest request, Model model) {
		System.out.println("inside update user load page controller");
		HttpSession session = (HttpSession) request.getSession();
		VehicleDetails vd = (VehicleDetails) session.getAttribute("vehicleDetail");
		model.addAttribute("vehicleDetail", vd);
		User u = (User) session.getAttribute("user");
		return new ModelAndView("userVehicleDetailsUpdate");
	}
	
	@PostMapping(value = "/user/updateVehicleDetails")
	public ModelAndView userUpdateVehicleDetails (HttpServletRequest request, HttpSession session,
			@ModelAttribute("vehicleDetail") VehicleDetails vehicleDetail, BindingResult bindingResult) {
		System.out.println ("inside update vehicleDetails post mappingt");
		String action = request.getParameter("action");
		int vehicleDetailID = Integer.parseInt(request.getParameter("vehicleDetailID"));
		System.out.println("vehicleDetailID submitted: " + vehicleDetailID);
		if (action.equals("Update")) {
			try {
				int updatedRows = vehicleDAO.updateVehicleDetialsByUser(vehicleDetail, vehicleDetailID);
				return new ModelAndView ("vehicleUpdateSuccess");
			} catch (UserException e) {
				System.out.println("unable to update vehicle details");
				return new ModelAndView ("error", "errorMessage", e);
			}
		} else if (action.equals("Delete")) {
			try {
			int deletedItemCount = vehicleDAO.deleteVehicleDetail(vehicleDetailID);
			if (deletedItemCount > 0) {
				return new ModelAndView ("vehicleUpdateSuccess");
			} else {
				return new ModelAndView ("error", "errorMessage", "Unable to delete the provided data");
			}
			} catch (UserException e ) {
				System.out.println("unable to delete vehicle details");
				return new ModelAndView ("error", "errorMessage", e);
			}
		}
		return null;
	}
	
	
	@GetMapping(value = "/user/vehicleDetails.*")
	public ModelAndView loadVehicleDetailsPage(HttpServletRequest request) {
		System.out.println("inside vehicleDetails controller");
		HttpSession session = (HttpSession) request.getSession();
		User u = (User) session.getAttribute("user");
		String id = request.getParameter("VehicleId");
		if (id != null) {
			int vehicleID = Integer.parseInt(id);
			try {
				VehicleDetails vehicleDetails = vehicleDAO.getVehicleDetailByID(vehicleID); 
				session.setAttribute("vehicleDetail", vehicleDetails);
				return new ModelAndView("vehicleDetails", "user", u);
			} catch (UserException e) {
				e.printStackTrace();
				System.out.println("vehicle detail fetch by id not working");
				return new ModelAndView("error", "errorMessage", e);
			}
		
		} else {
			VehicleDetails vd = (VehicleDetails) session.getAttribute("vehicleDetail");
			session.setAttribute("vehicleDetail", vd);
			return new ModelAndView("vehicleDetails", "user", u);
		}
	}
	
	@GetMapping(value = "/user/myListing.*")
	public ModelAndView myVehicleListing(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside vehicle Listing per user get controller");
		User u = (User) session.getAttribute("user");
		try {
			List<VehicleDetails> vdList = vehicleDAO.fetchVehicleListUser(u);
			if (vdList.size() > 0) {
				return new ModelAndView("vehicleDetailsLisitng", "vdList", vdList);
			} else {
				return new ModelAndView("vehicleDetailsLisitngNoResult", "vdList", vdList);
			}
		} catch (UserException e) {
			e.printStackTrace();
			System.out.println("Unable to fetch bid details for this user");
			return new ModelAndView("error", "errorMessage", e);
		}
	}
	
	@GetMapping(value = "/user/markAsSold.*")
	public ModelAndView markVehicleAsSold(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		System.out.println("inside mark as sold get controller");
		User u = (User) session.getAttribute("user");
		VehicleDetails vd = (VehicleDetails)session.getAttribute("vehicleDetail");
		return new ModelAndView ("markAsSold", "vehicleDetail", vd);

	}
	
	@PostMapping(value = "/user/markAsSold")
	public ModelAndView markVehicleAsSoldPost(HttpServletRequest request, HttpSession session,
			@ModelAttribute("vehicleDetail") VehicleDetails vehicleDetail, BindingResult bindingResult) {
		System.out.println("inside mark as sold get controller");
		User u = (User) session.getAttribute("user");
		int vehicleDetailID = Integer.parseInt(request.getParameter("vehicleID"));
		System.out.println("vehicleID submitted: " + vehicleDetailID);
		try {
			int updatedRows = vehicleDAO.updateSoldPrice(vehicleDetail, vehicleDetailID);
			return new ModelAndView ("vehicleUpdateSuccess");
		} catch (UserException e) {
			e.printStackTrace();
			System.out.println("unable to set sold price");
			return new ModelAndView("error", "errorMessage", e);
		}
	}

}