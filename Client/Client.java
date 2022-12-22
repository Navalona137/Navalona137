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
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import affichage.*;

public class Client implements Runnable{
    private final static int TIMEOUT = 3000; //3 sec 

    private final String name;
    private final Socket socket;

    private Fenetre fen;

    private Player player = null;

    public Client(String name, Fenetre f)  {
        this.fen = f;

        String ipAddr = "127.0.0.1";  //adresse ip localhost
        int port = 6412;
        Socket socket = null;
        System.out.println(String.format("%s wants to connect to %s:%d", name, ipAddr, port));

        boolean worked = false; 
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ipAddr, port), TIMEOUT);
            worked = true;
        } catch (SocketTimeoutException ex)  {
            System.out.println(ex.getMesùsage());
            
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
            } catch (SocketException | InterruptedException ex)  {
                System.out.println(ex.getMessage());
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void receive() throws IOException, ClassNotFoundException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = new String();
        if(Fenetre.CMD.startsWith("LIST_VIDEO")){
            answer = reader.readLine(); 
            
            try{
                ListVideo video = new ListVideo(this, answer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Fenetre.CMD = "*";
        }
        if(Fenetre.CMD.startsWith("LIST_MUSIC")){
            answer = reader.readLine(); 
            
            try{
                ListMusic music = new ListMusic(this, answer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Fenetre.CMD = "*";
        }
        if(Fenetre.CMD.startsWith("LIST_PHOTO")){
            answer = reader.readLine(); 
            
            try{
                ListPhoto photo = new ListPhoto(this, answer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Fenetre.CMD = "*";
        }
        if(Fenetre.CMD.startsWith("PLAY_PHOTO")){
            DataInputStream entree = new DataInputStream(socket.getInputStream());
            byte[] data = new byte[609538];
            
            try{
                Photo photo;
                if(entree.read(data) != -1)
                    photo = new Photo(this, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Fenetre.CMD = "*";
        }
        if(Fenetre.CMD.startsWith("PLAY_MUSIC")){
            play(); Fenetre.CMD = "*";
        }
        if(Fenetre.CMD.startsWith("STOP_MUSIC")){
            stop(); Fenetre.CMD = "*";
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

    public void play() throws IOException{
        new Thread() {
            @Override
            public void run() {
                try {
                    DataInputStream entree = new DataInputStream(socket.getInputStream());
                    player = new Player(entree);
                        
                    new Thread() {
                        @Override
                        public void run(){
                            try {
                                player.play();
                            } catch (JavaLayerException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }.start();
                } catch (IOException | JavaLayerException  ex) {
                    
                }
            }
        }.start();
    }

    public void stop(){
        if(player != null){
            player.close();
        }

    }
    
}
