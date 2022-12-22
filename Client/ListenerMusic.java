package listener;

import java.awt.event.*;
import javax.swing.*;

import affichage.*;
import client.*;

public class ListenerMusic implements MouseListener{
    public Client client;
    public ListMusic listM;
    
    public ListenerMusic(Client cl, ListMusic lm){ 
        this.client = cl;
        this.listM = lm;
    }

    public void mouseClicked(MouseEvent e){
        JButton scr = (JButton)e.getSource();

        if(scr.getText() == "retour"){
            try{
                listM.setVisible(false);
                listM.dispose();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    
        if(scr.getText() == "play"){
            System.out.println("play music");
            for(int i=0; i<listM.totalAudio+1; i++){
                if(listM.entree.getText().equals("Chanson numero : " + i)){ 
                    System.out.println("hiraaa");
                    try{
                        Fenetre.CMD = "PLAY_MUSIC";
                        client.send("music"+i);
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                        System.out.println("ex.getMessage()");
                    }
                }
            }
        }

        if(scr.getText() == "stop"){
            try{
                Fenetre.CMD = "STOP_MUSIC";
                client.send("stop");
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