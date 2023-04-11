package Client;

import server.models.Course;
import server.models.RegistrationForm;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ArrayList<Course> listeCours;


    public Client(int port) throws IOException {
        this.client = new Socket("127.0.0.1", port);

    }


    public void demanderListeCours(String session) {
        try {
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream.writeObject("CHARGER "+ session);
            listeCours = (ArrayList<Course>) objectInputStream.readObject();
            if (listeCours.isEmpty()) {
                System.out.println("Il n'y a aucun cours disponible pour la session " + session);
            } else {
                System.out.println("Voici la liste des cours disponibles pour la session " + session + " :");
                int num = 1;
                for (Course cours : listeCours) {
                    System.out.println(num + ". " + cours.getName() + "    " + cours.getCode());
                    num += 1;
                }
            }
            objectInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la récupération de la liste des cours : " + e.getMessage());
        }


    }
    public void demandeInscription(String choixSession) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir votre prénom :");
        String prenom = scanner.nextLine();
        System.out.println("Veuillez saisir votre nom :");
        String nom = scanner.nextLine();
        System.out.println("Veuillez saisir votre email :");
        String email = scanner.nextLine();
        String matricule = "";
        int i = 0;
        while (i == 0) {
            System.out.println("Veuillez saisir votre matricule :");
            matricule = scanner.nextLine();
            if (matricule.matches("[0-9]{6}")){
                i = 1;
            } else {
                System.out.println("Matricule invalide!");
            }
        }
        System.out.println("Veuillez saisir le code du cours :");
        String codeCours = scanner.nextLine();

        RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule,
                new Course(null, codeCours, choixSession));

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