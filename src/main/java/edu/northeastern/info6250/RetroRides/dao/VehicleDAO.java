package edu.northeastern.info6250.RetroRides.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import edu.northeastern.info6250.RetroRides.exception.UserException;
import edu.northeastern.info6250.RetroRides.pojo.User;
import edu.northeastern.info6250.RetroRides.pojo.VehicleDetails;
import jakarta.persistence.Query;

@Component
public class VehicleDAO extends DAO {
	
	public VehicleDetails uploadVehicleDetails(VehicleDetails vehicleDetail)
			throws UserException {
		try {
			begin();
			System.out.println("inside vehicle DAO");
			System.out.println(vehicleDetail.getCategory());
			getSession().save(vehicleDetail);
			commit();
			return vehicleDetail;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<VehicleDetails> getAllVehicles() throws UserException {
		try {
            begin();
            Query q = getSession().createQuery("from VehicleDetails where status IN ('ACTIVE', 'SOLD')");
            List<VehicleDetails> list = (List<VehicleDetails>) ((org.hibernate.query.Query) q).list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Exception while creating category:" + e.getMessage());
        }
		
	}
	
	public List<VehicleDetails> getAllUpdatedAddedVehicles() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from VehicleDetails where status IN ('ADDED', 'UPDATED')");
			List<VehicleDetails> list = (List<VehicleDetails>) ((org.hibernate.query.Query) q).list();
			System.out.println("list size: " + list.size());
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException ("Exception while fetching vehicleDetails List" + e);
		}
	} 
	
	public VehicleDetails getVehicleDetailByID(int id) throws UserException {
		try {
			begin ();
			Query q = getSession().createQuery("from VehicleDetails where vehicleID = :vehicleId");
			q.setParameter("vehicleId", id);
			VehicleDetails vd = (VehicleDetails) ((org.hibernate.query.Query) q).uniqueResult();
			commit();
			return vd;
		} catch (HibernateException e) {
			rollback();
			throw new UserException ("Exception while fetching vehicle details" + e);
		}
	}
	
	public int updateVehicleDetialsByUser(VehicleDetails vehicleDetail, int vehicleDetailID) throws UserException {
		 try {
	            begin();
	            System.out.println("inside DAo");
	            Query q = getSession().createQuery("update VehicleDetails set category = :category, brand = :brand, model = :model," +
	            "yearOfManufacture = :yearOfManufacture, mileage = :mileage, askPrice = :askPrice, status = :status ,color = :color, state = :state where vehicleID = :vehicleDetailID");
	            q.setParameter("vehicleDetailID", vehicleDetailID);
	            q.setParameter("category", vehicleDetail.getCategory());
	            q.setParameter("brand", vehicleDetail.getBrand());
	            q.setParameter("model", vehicleDetail.getModel());
	            q.setParameter("yearOfManufacture", vehicleDetail.getYearOfManufacture());
	            q.setParameter("askPrice", vehicleDetail.getAskPrice());
	            q.setParameter("color", vehicleDetail.getColor());
	            q.setParameter("mileage", vehicleDetail.getMileage());
	            q.setParameter("state", vehicleDetail.getState());
	            q.setParameter("status", "UPDATED");
	            int updatedRows = q.executeUpdate();
	            commit();
	            return updatedRows;
	        } catch (HibernateException e) {
	            System.out.println("inside exception" + e);
	        	rollback();
	            
	            throw new UserException("Could not save the product", e);
	        }
	}
	
    public int deleteVehicleDetail(int vehicleDetailID) throws UserException {
        try {
            begin();
            Query q = getSession().createQuery("delete from VehicleDetails where vehicleID = :vehicleId");
            q.setParameter("vehicleId", vehicleDetailID);
            int deletedVehicleDetail = q.executeUpdate();
            commit();
            return deletedVehicleDetail;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not delete the product", e);
        }
    }
    
    public int updateVehicleDetailStatusActiveIncative(VehicleDetails vehicleDetail, int vehicleDetailID) throws UserException {
        try {
            begin();
            Query q = getSession().createQuery("update VehicleDetails set status = :status where vehicleID = :vehicleId");
            q.setParameter("status", vehicleDetail.getStatus());
            q.setParameter("vehicleId", vehicleDetailID);
            int deletedVehicleDetail = q.executeUpdate();
            commit();
            return deletedVehicleDetail;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not update the status", e);
        }
    }
    
    public List<VehicleDetails> fetchVehicleListUser(User u ) 
    		throws UserException {
    	try {
    		   begin();
               Query q = getSession().createQuery("from VehicleDetails where user = :user");
               q.setParameter("user", u);
               List<VehicleDetails> vdList = (List<VehicleDetails>)((org.hibernate.query.Query) q).list();
               commit();
               return vdList;
    	} catch (HibernateException e) {
    		 rollback();
             throw new UserException("Could not update the status", e);
    	}
    }
    
    public int updateSoldPrice (VehicleDetails vehicleDetail, int vehicleDetailID) throws UserException {
    	  try {
              begin();
              Query q = getSession().createQuery("update VehicleDetails set soldPrice = :soldPrice, status = :status where vehicleID = :vehicleId");
              q.setParameter("soldPrice", vehicleDetail.getSoldPrice());
              q.setParameter("status", "SOLD");
              q.setParameter("vehicleId", vehicleDetailID);
              int updatedVehicleDetail = q.executeUpdate();
              commit();
              return updatedVehicleDetail;
          } catch (HibernateException e) {
              rollback();
              throw new UserException("Could not update the sold Price", e);
          }
    }

}
