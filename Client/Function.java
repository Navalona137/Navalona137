package function;

import listener.*;
import client.*;

import javazoom.jl.player.Player;
import java.io.*;
import java.nio.file.*;

public class Function{
    public Client client;
    public String path;
    private Player player;

    public Function(Client cl){
        this.client = cl;
    }

    public Function(Client cl, String message){
        this.client = cl;
        this.path = message;
    }

    public byte[] getBytes(File file){
        byte[] bytes = null;
        try{
            Path path = Paths.get(file.getAbsolutePath());
            bytes = Files.readAllBytes(path);
            System.out.println("Bytes" + bytes.length);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public void play(String path){
        try{
            FileInputStream zik = new FileInputStream(path);
            BufferedInputStream play = new BufferedInputStream(zik);
            player = new Player(play);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        new Thread(){
            @Override
            public void run(){
                try{
                    player.play();
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }.start();
    }

    public void stop(){
        System.out.println("efa akaiky");
        if(player!=null){
            System.out.println("tafiditra");
            player.close();
        }
    }

}