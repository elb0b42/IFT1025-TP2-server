package server;

import javafx.util.Pair;
import server.models.Course;
import server.models.RegistrationForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Class qui s'occupe de s'inscrire au bon cours de la session
 */
public class Server {

    /**
     * Déclare la commande pour s'enregistré à un cours
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";
    /**
     * Déclare la commande pour charger un cours
     */
    public final static String LOAD_COMMAND = "CHARGER";
    /**
     * Déclare la prise du server
     */
    private final ServerSocket server;
    /**
     * Déclare la prise du client
     */
    private Socket client;
    /**
     * Déclare l'entré d'information
     */
    private ObjectInputStream objectInputStream;
    /**
     *Déclare la sortie d'information
     */
    private ObjectOutputStream objectOutputStream;
    /**
     * Déclare le gestionnaire d'évenement
     */
    private final ArrayList<EventHandler> handlers;

    /**
     * Construis le server selon le port d'entrée
     * @param port port sur lequel le serveur est connecté
     * @throws IOException si une erreur arrive a la creation du serveur
     */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
    }

    /**
     * Ajoute un gestionair d'évenement h.
     * @param h le gestionair d'évenement que nous voulons rajouter.
     *
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }


    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     *
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transofmer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     La méthode gère les exceptions si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux.
     @param arg la session pour laquelle on veut récupérer la liste des cours
     */
    public void handleLoadCourses(String arg) {
        // TODO: implémenter cette méthode
        try {
            FileReader course = new FileReader("./src/main/java/server/data/cours.txt");
            BufferedReader reader = new BufferedReader(course);
            ArrayList<Course> cours_session = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] session = line.split("\t");
                if (session[2].equals(arg)) {
                    cours_session.add(new Course(session[0], session[1], session[2]));
                    System.out.println(cours_session);
                }
            }
            reader.close();
            objectOutputStream.writeObject(cours_session);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client.
     La méthode gére les exceptions si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() {
        // TODO: implémenter cette méthode
        try {

            RegistrationForm registrationForm = (RegistrationForm) objectInputStream.readObject();
            FileWriter fileWriter = new FileWriter("inscription.txt");
            fileWriter.write(registrationForm.getCourse().getSession()+"\t"+
                    registrationForm.getCourse().getCode()+"\t"+registrationForm.getMatricule()+"\t"+
                    registrationForm.getPrenom()+"\t"+registrationForm.getNom()+"\t"
                    +registrationForm.getEmail()+"\n");
            fileWriter.close();


            objectOutputStream.writeObject("votre inscription au cours"+ registrationForm.getCourse().getName() +"été " +
                    "enregistrée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

