package sample.multimodule.domain.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company-info", namespace = "com.concretepage")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountXML {

	@XmlAttribute(name = "id")
	private long id;
	@XmlElement(name = "names")
	private String number;
	@XmlElement(name = "type")
	private String type;
	@XmlElement(name = "CreditCard_Number")
	private String creditCardNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
