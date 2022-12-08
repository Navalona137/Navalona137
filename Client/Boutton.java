package boutton;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.util.*;


public class Boutton extends JPanel{
    private String valeur;

    public Boutton(String label){
        valeur = label;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//manala makorokoro

        g2.setColor(Color.gray);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 17));
        int w = g2.getFontMetrics().stringWidth(String.valueOf(valeur));
        g2.drawString(String.valueOf(valeur), getWidth()/2-w/2, getHeight()/2+10);//centre texte
    }

    public String getValeur(){
        return valeur;
    }

    public void setValeur(String value){
        valeur = value;
        repaint();
    }
}