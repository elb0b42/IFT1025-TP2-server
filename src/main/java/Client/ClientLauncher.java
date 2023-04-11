package Client;


import java.io.IOException;
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
        }
        System.out.println("Les cours offerts pendant la session d'" + choixSession + " sont:");
        client.demanderListeCours(choixSession);
        System.out.println(">Choix:");
        System.out.println("1. Consulter les cour offerts pour une autre session\n2. Inscription a un cours");

        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.println(">Choix:");
            choix = scanner.nextInt();
            if (choix != 1 && choix != 2) {
                System.out.println("Veuillez entrer un choix valide.");
            }
        }
        try{
            if (choix == 1) {
                client = new Client(1337);
                demande(client);
            }

            if (choix ==2) {
                client = new Client(1337);
                client.demandeInscription(choixSession);
            }
        } catch (IOException e) {
                System.out.println("Erreur lors de la récupération de connection au serveur: " + e.getMessage());
            }

    }
}


