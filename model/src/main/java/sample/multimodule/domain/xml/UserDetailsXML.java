package sample.multimodule.domain.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User_Details")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDetailsXML {

	@XmlElement(name = "id")
	private long userId;
	@XmlElement(name = "Frist-Name")
	private String firstName;
	@XmlElement(name = "Last-Name")
	private String lastName;
	@XmlElement(name = "Date-Of-Birth")
	private Date dob;

	@XmlElement(name="Security_Details")
	private SecurityDetailsXML securityDetails;
	
	@XmlElement(name="Address_Details")
	private AddressXML address;
	
	@XmlElement(name="Temporary_Address_Details")
	private TemporaryXML temporaryAddress;

	
	public long getUserId() {
		return userId;
	}

	public SecurityDetailsXML getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(SecurityDetailsXML securityDetails) {
		this.securityDetails = securityDetails;
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

	public AddressXML getAddress() {
		return address;
	}

	public void setAddress(AddressXML address) {
		this.address = address;
	}

	public TemporaryXML getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(TemporaryXML temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	
}
