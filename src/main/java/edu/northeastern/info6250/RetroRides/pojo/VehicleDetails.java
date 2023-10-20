package edu.northeastern.info6250.RetroRides.pojo;

import java.time.Year;

import org.springframework.web.multipart.MultipartFile;

import edu.northeastern.info6250.RetroRides.util.AttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "VehicleDetails")
public class VehicleDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "vehicleID", unique=true, nullable = false)
	private int vehicleID;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name = "askPrice", nullable = false)
	private float askPrice;
	
	@Column(name = "soldPrice")
	private float soldPrice;
	
	@Column (name="category", nullable = false)
	private String category;
	
	@Column (name="brand", nullable = false)
	private String brand;
	
	@Column (name="model", nullable = false)
	private String model;
	
	@Column (name="yearOfManufacture", nullable = false, columnDefinition = "smallint")
	@Convert( converter = AttributeConverter.class)
	private Year yearOfManufacture;
	
	@Column (name="color", nullable = false)
	private String color;
	
	@Column (name="state", nullable = false)
	private String state;
	
	@Column (name="mileage", nullable = false)
	private float mileage;
	
	@Column (name="fileName")
	private String fileName;
	
	@Column (name = "status", nullable = false)
	private String status;
	
	@Transient
	private MultipartFile photo;

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public float getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(float askPrice) {
		this.askPrice = askPrice;
	}

	public float getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(float soldPrice) {
		this.soldPrice = soldPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Year getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(Year yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(float mileage) {
		this.mileage = mileage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	

}
