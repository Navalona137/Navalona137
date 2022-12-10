package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import affichage.*;

public class Client implements Runnable{
    private final static int TIMEOUT = 2000;

    private final String name;
    private final Socket socket;

    private Fenetre fen;

    private String answer = new String();

    public Client(String name, Fenetre f)  {
        this.fen = f;

        String ipAddr = "127.0.0.1";
        int port = 6412;
        Socket socket = null;
        System.out.println(String.format("%s wants to connect to %s:%d", name, ipAddr, port));

        boolean worked = false; 
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddr, port), TIMEOUT);
            worked = true;
        } catch (SocketTimeoutException ex)  {
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        this.socket = socket;
        this.name = name;
        if (worked)  {
            new Thread(this).start();
        }
    }
    
    @Override
    public void run() {
        while (true)  {
            try {
                receive();
            } catch (SocketException ex)  {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void receive() throws IOException {
        answer = new String();
        if(Fenetre.CMD.equals("LIST")){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            answer = reader.readLine(); 
            fen.receive(answer);
        }else{
            //InputStream reader = new InputStream();
            Fenetre.MSGE = socket.getInputStream();
            //System.out.println("maka byte marina");
        }
        
    }

    public void send(String message) {
        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println(message);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
