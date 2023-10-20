package edu.northeastern.info6250.RetroRides.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.BidDetails;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.persistence.Query;

@Component
public class BidDAO extends DAO {
	
	public BidDetails getBidDetailsOnUserVehicle(VehicleDetails vd, User u)
			throws UserException {
		try {
            begin();
            Query q = getSession().createQuery("from BidDetails where user = :userID AND vehicleDetail = :vehicleID");
            q.setParameter("userID", u);
            q.setParameter("vehicleID", vd);
            BidDetails bidDetail = (BidDetails) ((org.hibernate.query.Query) q).uniqueResult();
            commit();
            return bidDetail;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating category:" + e.getMessage());
        }
	}
	
	public BidDetails insertBidDetails(BidDetails bidDetail)
		throws UserException {
		 
		try {
			begin();
			System.out.println("inside Bid Detail DAO: insert bid detail");
			System.out.println(bidDetail.getBidPrice());
			getSession().save(bidDetail);
			commit();
			return bidDetail;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public int updateBidDetails(BidDetails bidDetail) 
			throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("update BidDetails set bidPrice = :bidPrice where user = :user AND vehicleDetail = :vehicle");
			q.setParameter("bidPrice", bidDetail.getBidPrice());
			q.setParameter("user", bidDetail.getUser());
			q.setParameter("vehicle", bidDetail.getVehicleDetail());
			int updatedRows = q.executeUpdate();
			commit();
			return updatedRows;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<BidDetails> viewBideDetails(VehicleDetails vehicleDetail)
			throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from BidDetails where vehicleDetail = :vehicleDetail");
			q.setParameter("vehicleDetail", vehicleDetail);
			List<BidDetails> bidDetailList = (List<BidDetails>)((org.hibernate.query.Query) q).list();
			commit();
			return bidDetailList;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<BidDetails> fetchUserBidListing(User u)
			throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from BidDetails where user = :user");
			q.setParameter("user", u);
			List<BidDetails> bidDetailList = (List<BidDetails>)((org.hibernate.query.Query) q).list();
			commit();
			return bidDetailList;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
}
