package edu.northeastern.info6250.RetroRides.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.northeastern.info6250.RetroRides.dao.UserDAO;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.User;


@Component
public class UserValidator implements Validator {
	
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}
	
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email",	"Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "error.invalid.state",	"State Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.invalid.phoneNumber",	"Phone Number Required");

		UserDAO userDao=new UserDAO();
		try {
			int emailCheck=userDao.getUserWithSameEmail(user.getEmail());
			if(emailCheck>=1)
			{
				System.out.println("Value of emailCheck="+emailCheck);
				errors.rejectValue("email", "error.invalid.email", "This Email ID has already been registered");
			}
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
