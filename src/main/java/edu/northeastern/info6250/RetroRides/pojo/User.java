package edu.northeastern.info6250.RetroRides.pojo;



import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
    private int userId;
	
	@Column(nullable = false, length = 64)
	private String firstName;
	
	@Column(nullable = false, length = 64)
	private String lastName;
     
    @Column(nullable = false, unique = true, length = 64)
    private String email;
    
    @Column(nullable = false, length = 45)
	private String role;
     
    @Column(nullable = false, length = 64)
    private String password;
    
    @Column(nullable = false, length = 64)
    private String phoneNumber;
    
    @Column(nullable = false, length = 64)
    private String state;
    
//    @OneToMany(mappedBy="user")
//    private Set<VehicleDetails> vehicleList;

    
	public User(String firstName, String lastName, String email, String password, String role, String phoneNumber, 
			String state) {
		this.email = email;
		this.password = password;
		this.role=role;
		this.state = state;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.lastName = lastName;
	}

	public User() {
	
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
    
}
