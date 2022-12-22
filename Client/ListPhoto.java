package affichage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;

import listener.*;
import client.*;
import boutton.*;

public class ListPhoto extends JFrame{
    public Client client;
    public JTextField entree;
    public int totalPhoto;

    public ListPhoto(Client cl, String message) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        this.client = cl;

        setTitle("Fenetre");
		setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 15, 5, 15));

    //-----------------------------------------------------------------------------------------------------------------------
        JPanel titrePanel = new JPanel();
        JLabel titre = new JLabel("Liste des photos");
        titre.setFont(new Font("Arial", Font.BOLD, 25));
        titrePanel.add(titre);
        panel.add(titrePanel, BorderLayout.NORTH);
    
    //-------------------------------------------------------------------------------------------------------------------------
        JPanel contenu = new JPanel();
        contenu.setLayout(new GridLayout(4, 4, 5, 5));
        contenu.setBorder(new EmptyBorder(5, 5, 5, 5));

        String[] messages = message.split(",");
        totalPhoto = messages.length;
        Boutton[] label = new Boutton[messages.length];
        for(int i=0; i<messages.length; i++){
            if(i>0){
                String title = i + " - " + messages[i];
                label[i] = new Boutton(title); 
                contenu.add(label[i]);
            }
        }
        panel.add(contenu, BorderLayout.CENTER);

    //-------------------------------------------------------------------------------------------------------------------------------------------
        ListenerPhoto listen = new ListenerPhoto(client, this);

        JPanel bouttonPanel = new JPanel();
        bouttonPanel.setLayout(new BorderLayout());
        bouttonPanel.setBackground(Color.gray);

        JPanel retourPanel = new JPanel();
        JButton retour = new JButton("retour");
        retourPanel.add(retour);
        retour.addMouseListener(listen);

        JPanel entreePanel = new JPanel();
        entree = new JTextField("Photo numero : ");
        entreePanel.add(entree);

        JPanel playPanel = new JPanel();
        JButton play = new JButton("Afficher");
        play.addMouseListener(listen);
        playPanel.add(play);
        
        bouttonPanel.add(playPanel, BorderLayout.EAST);
        bouttonPanel.add(entreePanel, BorderLayout.CENTER);
        bouttonPanel.add(retourPanel, BorderLayout.WEST);
        panel.add(bouttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

}