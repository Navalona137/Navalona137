package affichage;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.net.UnknownHostException;

import client.*;
import listener.*;

public class Fenetre extends JFrame{
    public Client client;
    public String msg;

    public Fenetre() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        setTitle("Fenetre");
		setSize(800, 500);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton music = new JButton("Music");
        JButton photo = new JButton("Photo");
        JButton video  = new JButton("Video");

        client = new Client("Rakoto", this);
        Listener listen = new Listener(client, this);
        music.addMouseListener(listen);
        photo.addMouseListener(listen);
        video.addMouseListener(listen);

        panel.add(music);
        panel.add(photo);
        panel.add(video);

        add(panel);
        setVisible(true);
    }

    public void receive(String message) {
        msg = new String();
        msg = message;
        //System.out.println(msg);
    }
}