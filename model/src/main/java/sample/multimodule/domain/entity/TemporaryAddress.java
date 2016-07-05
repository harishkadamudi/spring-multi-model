package sample.multimodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Temporary_Address")
public class TemporaryAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "temp_address_id")
	private Long id;

	@Column(name = "houseNumber", nullable = false)
	private int houseNumber;

	@Column(name = "streetAddress", nullable = false)
	private String streetAddress;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "zipCode", nullable = false)
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

	@Override
	public String toString() {
		return "TemporaryAddress [id=" + id + ", houseNumber=" + houseNumber + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}

	
}
