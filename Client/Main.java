package client;

import affichage.*;

public class Main {

	/*public Main() {
		Client client = new Client("Navalona");
		client.send("Bonjour");
	}*/

	public static void main(String[] args){
        try{
            Fenetre main = new Fenetre();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
