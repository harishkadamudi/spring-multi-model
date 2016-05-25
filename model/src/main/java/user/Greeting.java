package user;

public class Greeting {

	private long id;
	private String envelop;
	
	private Description description;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnvelop() {
		return envelop;
	}

	public void setEnvelop(String envelop) {
		this.envelop = envelop;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", envelop=" + envelop + ", description=" + description + "]";
	}
	
	
}
