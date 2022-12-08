package server;

public class Main {

	public Main() {
		//System.out.println(System.getProperty("user.dir")+"/assets/hira");
		new Thread(new Server()).start();
	}

	public static void main(String[] args) {
		new Main();
	}

}
