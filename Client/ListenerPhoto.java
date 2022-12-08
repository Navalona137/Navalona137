package listener;

import java.awt.event.*;
import javax.swing.*;

import affichage.*;
import client.*;

public class ListenerPhoto implements MouseListener{
    public Client client;
    public Fenetre fen;
    public ListPhoto listP;

    public ListenerPhoto(Client cl, Fenetre f, ListPhoto lp){
        this.client = cl;
        this.fen = f;
        this.listP = lp;
    }

    public void mouseClicked(MouseEvent e){
        JButton scr = (JButton)e.getSource();

        if(scr.getText() == "retour"){
            try{
                listP.setVisible(false);
                listP.dispose();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        if(scr.getText() == "Afficher"){
            System.out.println("affiche photo");
            if(listP.entree.getText().equals("Photo numero : 1")){ 
                try{
                    client.send("photo0");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listP.entree.getText().equals("Photo numero : 2")){ 
                try{
                    client.send("photo1");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listP.entree.getText().equals("Photo numero : 3")){ 
                try{
                    client.send("photo2");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listP.entree.getText().equals("Photo numero : 4")){ 
                try{
                    client.send("photo3");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listP.entree.getText().equals("Photo numero : 5")){ 
                try{
                    client.send("photo4");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
            if(listP.entree.getText().equals("Photo numero : 6")){ 
                try{
                    client.send("photo5");
                    Photo photo = new Photo(client, fen.msg);
                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
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