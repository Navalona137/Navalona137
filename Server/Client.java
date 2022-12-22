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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class Client implements Runnable{
    private final Socket socket;

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

    public Client(Socket socket)  {
        this.socket = socket;


        String pat = path.replace("\\", "/");
        String pathAudio = pat + "/assets/hira";
        String pathPhoto = pat + "/assets/photo";
        String pathVideo = pat + "/assets/video";

        myFileAudio = new File(pathAudio);
        listeAudio = myFileAudio.listFiles();
        longueurAudio = listeAudio.length;
        System.out.println("File audio: " + longueurAudio);

        myFilePhoto = new File(pathPhoto);
        listePhoto = myFilePhoto.listFiles();
        longueurPhoto = listePhoto.length;
        System.out.println("File photo: " + longueurPhoto);

        myFileVideo = new File(pathVideo);
        listeVideo = myFileVideo.listFiles();
        longueurVideo = listeVideo.length;
        System.out.println("File video: " + longueurVideo);
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void run(){
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

    private void receive() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = reader.readLine(); 
        System.out.println(answer + " izy");

        if(answer.equalsIgnoreCase("music")){
            music = new String();
            for(int i=0; i<longueurAudio; i++){
                try{
                    music = music + " , " + listeAudio[i].getName();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
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
            send(video);
        }

        for(int i=0; i<longueurAudio; i++){
            if(answer.equalsIgnoreCase("music"+i)){
                nom = new String();
                try{
                    nom = listeAudio[i-1].getPath();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }                    
                sendObj(nom);
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
                sendObj(nom);
            }
        }

        if(answer.equalsIgnoreCase("stop")){
            nom = new String();
            for(int i=0; i<longueurAudio; i++){
                try{
                    nom = "stop";
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
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

    public void sendObj(String filename) throws FileNotFoundException, IOException{
        File file = new File(filename);
        byte[] data = new byte[(int)file.length()];
        //System.out.println("len photo "+(int)file.length());
        FileInputStream fis = new FileInputStream(file);
  
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        if((fis.read(data, 0, (int)file.length())) != -1){
            try{
                dout.write(data); 
                dout.flush();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
