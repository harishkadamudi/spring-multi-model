package sample.multimodule.user.impl;

public enum StatusConstants {

	JUST_REGISTERED("JUST_REGISTERED"), // calls constructor with value 3
	USER_DETAILS_SENT("USER_DETAILS_SENT"), // calls constructor with value 2
	ACKNOWLEDGE("ACKNOWLEDGE") // calls constructor with value 1
	; // semicolon needed when fields / methods follow

	private final String status;

	StatusConstants(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
}
