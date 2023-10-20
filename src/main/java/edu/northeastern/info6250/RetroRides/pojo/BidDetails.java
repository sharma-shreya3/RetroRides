package edu.northeastern.info6250.RetroRides.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BidDetails")
public class BidDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bidId", unique=true, nullable = false)
	private int bidId;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="userId", nullable = false)
	private User user;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="vehicleId", nullable = false)
	private VehicleDetails vehicleDetail;
	
	@Column(name="bidPrice")
	private float bidPrice;

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VehicleDetails getVehicleDetail() {
		return vehicleDetail;
	}

	public void setVehicleDetail(VehicleDetails vehicleDetail) {
		this.vehicleDetail = vehicleDetail;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

}
