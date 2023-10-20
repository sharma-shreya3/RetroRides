package edu.northeastern.info6250.RetroRides.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.User;
import jakarta.persistence.Query;

@Component
public class UserDAO extends DAO {
	
	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			//Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getFirstName(), u.getLastName(), u.getEmail() ,u.getPassword(), u.getRole(),
					u.getPhoneNumber(), u.getState());
			System.out.println("First Name="+u.getFirstName());
			System.out.println("Last Name="+u.getLastName());
			System.out.println("Role="+u.getRole());
			System.out.println("Email="+u.getEmail());
			System.out.println("State="+u.getState());
			System.out.println("phonenumber"+u.getPhoneNumber());
			System.out.println("password"+u.getPassword());
			System.out.println("User="+user);
			
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setRole(u.getRole());
			user.setPassword(u.getPassword());
			user.setPhoneNumber(u.getPhoneNumber());
			user.setState(u.getState());
			user.setEmail(u.getEmail());
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	public int getUserWithSameEmail(String email) throws UserException
	{
		try {
			begin();
			Query query = getSession().createQuery("from User where email= :email");
			query.setParameter("email", email);		
			List<User> list= ((org.hibernate.query.Query) query).list();
			commit();
			return list.size();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + email, e);
		}
	}
	
	public User getLoggedInUser(String email, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where email = :email and password = :password");
			q.setParameter("email", email);
			q.setParameter("password", password);	
			System.out.println("email" + email);
			System.out.println("password" + password);
			User user = (User) ((org.hibernate.query.Query) q).uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + email, e);
		}
	}
}
