package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    private ArrayList<Client> clientlist;

    private int id = 0;

    public Server()  {

    }
    
    @Override
    public void run() {
        int port = 6412;
        try (ServerSocket server = new ServerSocket(port)) {
            clientlist = new ArrayList<Client>();
            System.out.println("Waiting for the client request");
            while (true) {
                Socket socket = server.accept();

                synchronized (clientlist) {
                    Client client = new Client(socket, id);
                    new Thread(client).start();

                    clientlist.add(client);
                    System.out.println("Client id: " + id + " accepted!");
                    client.send("voaray!");

                    id++; 
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
