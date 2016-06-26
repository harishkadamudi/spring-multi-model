package sample.multimodule.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Embeddable
@Entity
public class SecurityDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="security_id")
	private long securityId;
	
	private String socialSecurityNumber;

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

}
