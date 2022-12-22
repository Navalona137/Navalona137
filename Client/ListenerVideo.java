package listener;

import java.awt.event.*;
import javax.swing.*;

import affichage.*;
import client.*;

public class ListenerVideo implements MouseListener{
    public Client client;
    public ListVideo listV;

    public ListenerVideo(Client cl, ListVideo lv){
        this.client = cl;
        this.listV = lv;
    }

    public void mouseClicked(MouseEvent e){
        JButton scr = (JButton)e.getSource();

        if(scr.getText() == "retour"){
            try{
                listV.setVisible(false);
                listV.dispose();
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