package sample.multimodule.domain.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Temporary_Address_Details")
@XmlAccessorType(XmlAccessType.FIELD)
public class TemporaryXML {
	
	@XmlElement(required= false)
	private Long id;
	@XmlElement(name="House_Number")
	private int houseNumber;
	@XmlElement(name="Street_Address")
	private String streetAddress;
	@XmlElement(name="City")
	private String city;
	@XmlElement(name="State")
	private String state;
	@XmlElement(name="ZipCode")
	private String zipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
