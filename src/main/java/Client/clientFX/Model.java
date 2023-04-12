package Client.clientFX;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Model {
    private ArrayList<Course> listeCours;
    public ArrayList<Course> demanderListeCours(String session) {

        try {
            Socket client = new Socket("127.0.0.1", 1337);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream.writeObject("CHARGER "+ session);
            ArrayList<Course> listeCours = (ArrayList<Course>) objectInputStream.readObject();

            objectInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la récupération de la liste des cours : " + e.getMessage());
        }
        return listeCours;
    }
    public void demandeInscription(RegistrationForm form) {

        try {
            Socket client = new Socket("127.0.0.1", 1337);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
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
