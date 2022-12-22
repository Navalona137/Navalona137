package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    public Server()  {

    }
    
    @Override
    public void run() {
        int port = 6412;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Waiting for the client request");
            while (true) {
                Socket socket = server.accept();

                synchronized (socket) {
                    Client client = new Client(socket);
                    new Thread(client).start();
                    System.out.println("Client accepted!");
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
