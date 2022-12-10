package affichage;

import listener.*;
import client.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Photo extends JFrame{
    public Client client;

    public Photo(Client cl, String message){
        this.client = cl;

        setTitle("Fenetre");
		setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 15, 5, 15));

        JPanel sary = new JPanel(); 
        JLabel table = new JLabel();
        table.setIcon(new ImageIcon(message)); 
        table.setBounds(100,0,0,0);
        sary.add(table);

        Listener listen = new Listener(client, this);
        JPanel boutton = new JPanel();
        JButton retour = new JButton("retour");
        retour.addMouseListener(listen);
        boutton.add(retour);

        panel.add(boutton, BorderLayout.NORTH);
        panel.add(sary, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}