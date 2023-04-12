package clientSimple;


import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientLauncher {
    public static void main(String[] args) {
        System.out.println("*** Bienvenue au portail d'inscription de cours de l'UDEM ***");
        Client client;
        try {
            client = new Client(1337);
            demande(client);
        } catch (Exception e){
            System.out.println("Erreur lors de la connection au serveur : " + e.getMessage());
        }
    }
    public static void demande(Client client) {


        System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste des cours : \n" +
                "1. Automne\n2. Hiver\n3. Été");
        System.out.println(">Choix:");
        Scanner scanner = new Scanner(System.in);
        int numSession = scanner.nextInt();
        String choixSession = new String("");
        try {
            switch (numSession) {
                case 1:
                    choixSession = "Automne";
                    break;
                case 2:
                    choixSession = "Hiver";
                    break;
                case 3:
                    choixSession = "Ete";
                    break;
                default:
                    System.out.println("Veuillez entrer un chiffre valide pour la session.");
                    demande(client);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            demande(client);
        }
        ArrayList<Course> listeCours = client.demanderListeCours(choixSession);
        if (listeCours.isEmpty()) {
            System.out.println("Il n'y a aucun cours disponible pour la session " + choixSession);
        } else {
            System.out.println("Voici la liste des cours disponibles pour la session " + choixSession + " :");
            int num = 1;
            for (Course cours : listeCours) {
                System.out.println(num + ". " + cours.getName() + "    " + cours.getCode());
                num += 1;
            }
        }
        System.out.println(">Choix:");
        System.out.println("1. Consulter les cours offerts pour une autre session\n2. Inscription a un cours");

        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.println(">Choix:");
            choix = scanner.nextInt();
            if (choix != 1 && choix != 2) {
                System.out.println("Veuillez entrer un choix valide.");
            }
        }
        try {
            if (choix == 1) {
                client = new Client(1337);
                demande(client);
            }

            if (choix == 2) {
                client = new Client(1337);
                RegistrationForm form = inscription(choixSession, listeCours);
                client.demandeInscription(form);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la récupération de connection au serveur: " + e.getMessage());
        }
    }
    public static RegistrationForm inscription(String choixSession, ArrayList<Course> listeCours){
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
        String codeCours = "";
        i = 0;
        while (i == 0) {
            System.out.println("Veuillez saisir le code du cours :");
            codeCours = scanner.nextLine();
            for (Course cours : listeCours) {
                if (codeCours.equals(cours.getName())) {
                    i = 1;
                }
            }
            if (i == 0) {
                System.out.println("Le cours " + codeCours + " n'est pas disponible à la session d'" + choixSession);
            }

        }

        RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule,
                new Course(null, codeCours, choixSession));
        return form;
    }
}


