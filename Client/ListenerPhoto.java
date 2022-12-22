package listener;

import java.awt.event.*;
import javax.swing.*;

import affichage.*;
import client.*;

public class ListenerPhoto implements MouseListener{
    public Client client;
    public ListPhoto listP;

    public ListenerPhoto(Client cl, ListPhoto lp){
        this.client = cl;
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
            for(int i=0; i<listP.totalPhoto+1; i++){
                if(listP.entree.getText().equals("Photo numero : " + i)){ 
                    try{
                        Fenetre.CMD = "PLAY_PHOTO";
                        client.send("photo"+i);
                    }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
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