package affichage;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.UnknownHostException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import javax.swing.border.EmptyBorder;

import client.*;
import listener.*;

public class Fenetre extends JFrame{
    public Client client;
    public static String CMD = "";

    public Fenetre() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        setTitle("Fenetre");
		setSize(900, 600);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 15, 5, 15));

        JPanel titrePanel = new JPanel();
        JLabel title = new JLabel("Bienvenue");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        titrePanel.add(title);

        JPanel bouttonPanel = new JPanel();
        panel.add(titrePanel, BorderLayout.NORTH);
        panel.add(bouttonPanel, BorderLayout.CENTER);

        JButton music = new JButton("Music");
        JButton photo = new JButton("Photo");
        JButton video  = new JButton("Video");

        client = new Client("Rakoto", this);
        Listener listen = new Listener(client, this);
        music.addMouseListener(listen);
        photo.addMouseListener(listen);
        video.addMouseListener(listen);

        bouttonPanel.add(music);
        bouttonPanel.add(photo);
        bouttonPanel.add(video);



        add(panel);
        setVisible(true);
    }
}