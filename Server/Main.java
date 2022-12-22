package server;

public class Main {

	public Main() {
		new Thread(new Server()).start();
	}

	public static void main(String[] args) {
		new Main();
	}

}
