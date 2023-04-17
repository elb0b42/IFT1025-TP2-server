package Client.clientSimple;

import server.models.Course;
import server.models.RegistrationForm;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


    public ArrayList<Course> demanderListeCours(String session) {
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream.writeObject("CHARGER "+ session);
            listeCours = (ArrayList<Course>) objectInputStream.readObject();

            objectInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la récupération de la liste des cours : " + e.getMessage());
        }
        return listeCours;
    }
    public void demandeInscription(RegistrationForm form) {


        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream.writeObject("INSCRIRE");
            objectOutputStream.writeObject(form);
            String message = (String) objectInputStream.readObject();
            System.out.println(message);
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

}