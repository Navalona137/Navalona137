package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.*;

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

        if(answer.equalsIgnoreCase("music0")){
            nom = new String();
            try{
                nom = listeAudio[0].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("music1")){
            nom = new String();
            try{
                nom = listeAudio[1].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("music2")){
            nom = new String();
            try{
                nom = listeAudio[2].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("music3")){
            nom = new String();
            try{
                nom = listeAudio[3].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }

        if(answer.equalsIgnoreCase("photo0")){
            nom = new String();
            try{
                nom = listePhoto[0].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("photo1")){
            nom = new String();
            try{
                nom = listePhoto[1].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("photo2")){
            nom = new String();
            try{
                nom = listePhoto[2].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("photo3")){
            nom = new String();
            try{
                nom = listePhoto[3].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("photo4")){
            nom = new String();
            try{
                nom = listePhoto[4].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
        }
        if(answer.equalsIgnoreCase("photo5")){
            nom = new String();
            try{
                nom = listePhoto[5].getPath();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }                    
            System.out.println(nom);
            send(nom);
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
