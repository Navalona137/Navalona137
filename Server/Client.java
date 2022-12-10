package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.FileInputStream;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.ClassNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.*;

public class Client implements Runnable{

    private final Socket socket;
    private final int id;

    private String name;

    private String path = System.getProperty("user.dir");
    
    private File myFileAudio;
    private File[] listeAudio;
    private int longueurAudio;
    private String music = new String();

    private File myFilePhoto;
    private File[] listePhoto;
    private int longueurPhoto;
    private String photo = new String();

    private File myFileVideo;
    private File[] listeVideo;
    private int longueurVideo;
    private String video = new String();

    private String nom = new String();

    private byte[] byteAudio;

    public Client(Socket socket, int id)  {
        this.socket = socket;
        this.id = id;

        String pat = path.replace("\\", "/");
        String pathAudio = pat + "/assets/hira";
        String pathPhoto = pat + "/assets/photo";
        String pathVideo = pat + "/assets/video";

        myFileAudio = new File(pathAudio);
        listeAudio = myFileAudio.listFiles();
        longueurAudio = listeAudio.length;

        myFilePhoto = new File(pathPhoto);
        listePhoto = myFilePhoto.listFiles();
        longueurPhoto = listePhoto.length;

        myFileVideo = new File(pathVideo);
        listeVideo = myFileVideo.listFiles();
        longueurVideo = listeVideo.length;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = reader.readLine(); 
        System.out.println(answer);

        if(answer.equalsIgnoreCase("music")){
            music = new String();
            for(int i=0; i<longueurAudio; i++){
                try{
                    music = music + " , " + listeAudio[i].getName();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(music);
            send(music);
        }
        if(answer.equalsIgnoreCase("photo")){
            photo = new String();
            for(int i=0; i<longueurPhoto; i++){
                try{
                    photo = photo + " , " + listePhoto[i].getName();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(photo);
            send(photo);
        }
        if(answer.equalsIgnoreCase("video")){
            video = new String();
            for(int i=0; i<longueurVideo; i++){
                try{
                    video = video + " , " + listeVideo[i].getName();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(video);
            send(video);
        }

        for(int i=0; i<longueurAudio; i++){
            if(answer.equalsIgnoreCase("music"+i)){
                nom = new String();
                try{
                    nom = listeAudio[i-1].getAbsolutePath();
                    byteAudio = loadAudio(listeAudio[i-1]);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }                    
                System.out.println(nom);
                sendByte(byteAudio);
            }
        }

        for(int i=0; i<longueurPhoto; i++){
            if(answer.equalsIgnoreCase("photo"+i)){
                nom = new String();
                try{
                    nom = listePhoto[i-1].getPath();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }                    
                System.out.println(nom);
                send(nom);
            }
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

    public void sendByte(byte[] audio) {
        try {
            System.out.println("mandeh sendByte");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.println(audio);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("writer.println(audio)");
        }
    }

    public byte[] loadAudio(File file) {
        byte[] bytes = null;
        try{
           Path path = Paths.get(file.getAbsolutePath());
           bytes = Files.readAllBytes(path);
           System.out.println(bytes.length);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("loadAudio error");
        }/*catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }*/
        return bytes;
    }
}
