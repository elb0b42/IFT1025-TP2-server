package Client;

import server.models.Course;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.IOException;
public class Client {
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ArrayList<Course> listeCours;


    public Client(int port) throws IOException {
        this.client = new Socket("127.0.0.1", port);
    }
    public void demarer() {
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            demande();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void demande() {
        System.out.println("*** Bienvenue au portail d'inscription de cours de l'UDEM");
        System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours : \n1. Automne\n2.Hiver\n3. Été");

    }
}