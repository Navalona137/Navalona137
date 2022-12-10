package listener;

import java.awt.event.*;
import javax.swing.*;

import function.*;
import affichage.*;
import client.*;

public class ListenerMusic implements MouseListener{
    public Client client;
    public Fenetre fen;
    public ListMusic listM;
    public Function fonct;
    
    public ListenerMusic(Client cl, Fenetre f, Function fu, ListMusic lm){ 
        this.client = cl;
        this.fen = f;
        this.fonct = fu;
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
            for(int i=0; i<listM.totalAudio; i++){
                if(listM.entree.getText().equals("Chanson numero : " + i)){ 
                    System.out.println("hiraaa");
                    try{
                        Fenetre.CMD = "PLAY";
                        client.send("music"+i);

                        System.out.println("vita send");
                        if(Fenetre.MSGE != null){
                            System.out.println("tsy null MSGE");
                            fonct.play(Fenetre.MSGE);
                        }
                            
                        else    
                            System.out.println("null MSGE");
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                        System.out.println("ex.getMessage()");
                    }
                }
            }


            /*if(listM.entree.getText().equals("Chanson numero : 1")){ 
                try{
                    client.send("music0");
                    System.out.println(fen.msg);
                    fonct.play(fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listM.entree.getText().equals("Chanson numero : 2")){ 
                try{
                    client.send("music1");
                    System.out.println(fen.msg);
                    fonct.play(fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listM.entree.getText().equals("Chanson numero : 3")){ 
                try{
                    client.send("music2");
                    System.out.println(fen.msg);
                    fonct.play(fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listM.entree.getText().equals("Chanson numero : 4")){ 
                try{
                    client.send("music3");
                    System.out.println(fen.msg);
                    fonct.play(fen.msg);
                    //listM.play(fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }*/
            System.out.println("tsy tafiiditra");
        }

        if(scr.getText() == "stop"){
            try{
                fonct.stop();
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