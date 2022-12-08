package listener;

import java.awt.event.*;
import javax.swing.*;

import affichage.*;
import client.*;

public class Listener implements MouseListener{
    public Client client;
    public Fenetre fen;
    public Photo photo;

    public Listener(Client cl, Fenetre f){
        this.client = cl;
        this.fen = f;
    }

    public Listener(Client cl, Photo ph){
        this.client = cl;
        this.photo = ph;
    }

    public void mouseClicked(MouseEvent e){
        JButton scr = (JButton)e.getSource();

        if(scr.getText() == "Music"){
            try{
                client.send("music");
                ListMusic music = new ListMusic(client, fen, fen.msg);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        if(scr.getText() == "Photo"){
            try{
                client.send("photo");
                ListPhoto photo = new ListPhoto(client, fen, fen.msg);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        if(scr.getText() == "Video"){
            try{
                client.send("video");
                ListVideo video = new ListVideo(client, fen, fen.msg);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(scr.getText() == "retour"){
            try{
                photo.setVisible(false);
                photo.dispose();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    } 

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    } 

    public void mousePressed(MouseEvent e){

    } 

    public void mouseReleased(MouseEvent e){

    }
}