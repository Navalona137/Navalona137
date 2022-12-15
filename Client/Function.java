package function;

import listener.*;
import client.*;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;
import java.io.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.nio.file.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Function{
    public Client client;
    public String path;
    private Player player = null;

    public Function(Client cl){
        this.client = cl;
    }

    public Function(Client cl, String message){
        this.client = cl;
        this.path = message;
    }

    public void play(InputStream play){
        byte[] bytes = new byte[5756062];
        try{
            DataInputStream dis = new DataInputStream(play);
            DataInputStream mus;
            while(dis.read()!=-1){
                dis.read(bytes);
                mus = new DataInputStream(new ByteArrayInputStream(bytes));
                player = new Player(mus);
            //byte[] tabBytes = getBytes(path);
            //String zikk = new String(tabBytes);
            //FileInputStream zik = new FileInputStream(zikk);
                //System.out.println("miditra 1");
                //BufferedInputStream plays = new BufferedInputStream(play);
            //int len = plays.read();
            //System.out.println(len+" miditra 2");
                //player = new Player(plays);
                if(player == null){
                    System.out.println("player null");
                }
        /*}catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Fonction : tsy mandeh");
        }*/

                new Thread(){
                    @Override
                    public void run(){
                        try{
                            System.out.println("tokony handeh");
                            player.play();
                        }catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                }.start();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Fonction : tsy mandeh");
        }
    }
    
    public void stop(){
        System.out.println("efa akaiky");
        if(player!=null){
            System.out.println("tafiditra");
            player.close();
        }
    }

}