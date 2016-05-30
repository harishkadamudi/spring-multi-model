package sample.multimodule.jms;

public class Receiver {

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
