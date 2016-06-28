package sample.multimodule.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="User_Details")
public class UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String firstName;
	private String lastName;
	private Date dob;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="security_id")
	private SecurityDetails securityDetails;
	
/*	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="temp_address_id")
	private TemporaryAddress temporaryAddress;*/
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public SecurityDetails getSecurityDetails() {
		return securityDetails;
	}
	public void setSecurityDetails(SecurityDetails securityDetails) {
		this.securityDetails = securityDetails;
	}
	
}
