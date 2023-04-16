package Client.clientFX;

import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * gère les opérations à faire sur l'interface.
 */
public class Model {
    /**
     *Crée un nouvelle liste de cours.
     */
    public ArrayList<Course> listeCours;

    /**
     * fonction qui va chercher la liste des cours.
     * @param session session dans la quelle les cours doivent être donné.
     * @return une liste de tout les cours donner dans la session demandé
     */
    public ArrayList<Course> demanderListeCours(String session) {

        try {
            Socket client = new Socket("127.0.0.1", 1337);
            ObjectOutputStream objectOutputStream;
            ObjectInputStream objectInputStream;
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

    /**
     * Fonction qui prend tout les information du client pour l'inscription.
     * @param form Formulaire qui contient les information du client.
     * @return retourne l'inscription du client.
     */
    public String demandeInscription(RegistrationForm form) {

        String message = "";
        String prenom = "";
        String nom = "";
        String email = "";
        String matricule = "";
        Course cours = form.getCourse();
        String erreurCours = "";

        if (cours == null) {
            erreurCours = "Veuillez choisir un cours\n";
        }

        if (form.getPrenom().isEmpty()) {
            prenom = "Veuiller entrer votre prénom\n";
        }
        if (form.getNom().isEmpty()){
            nom = "Veuiller entrer votre nom\n";
        }
        if (!form.getEmail().matches("[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-A-Za-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?")) {
            email = "Veuiller entrer un email valide\n";
        }
        if (!form.getMatricule().matches(("^[0-9]{6}$") )) {
            matricule = "Veullier entrer un matricule de 6 chiffre\n";
        }
        message = erreurCours + prenom + nom  + email + matricule;
        if (!message.isEmpty()) {
            return message.trim();
        }
        try {
            Socket client = new Socket("127.0.0.1", 1337);
            ObjectOutputStream objectOutputStream;
            ObjectInputStream objectInputStream;
            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectInputStream = new ObjectInputStream(client.getInputStream());
            objectOutputStream.writeObject("INSCRIRE");
            objectOutputStream.writeObject(form);
            message = (String) objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
            return "Erreur lors de l'inscription : " + e.getMessage();
        }
    return null;
    }

}
