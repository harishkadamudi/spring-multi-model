package sample1.multimodule.jms;

public class Receiver {

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
