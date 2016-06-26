package sample.multimodule.domain.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ezyy", namespace = "ezyy")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDetailsXML {

	@XmlAttribute(name = "id")
	private long userId;
	@XmlAttribute(name = "Frist-Name")
	private String firstName;
	@XmlAttribute(name = "Last-Name")
	private String lastName;
	@XmlAttribute(name = "Date-Of-Birth")
	private Date dob;
	/*@XmlElementRef
	private SecurityDetailsXML securityDetails;*/
	@XmlAttribute(name = "Social-Security-Number")
	private String socialSecurityNumber;
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

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

/*	public SecurityDetailsXML getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(SecurityDetailsXML securityDetails) {
		this.securityDetails = securityDetails;
	}*/

}
