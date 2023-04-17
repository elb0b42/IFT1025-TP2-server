package Client.clientFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;
import server.models.RegistrationForm;

import java.util.ArrayList;

/**
 * Exécute les saisis du client.
 */
public class Controler {
    private Model model;
    private View view;

    /**
     * connect La classe View et controler ensemble.
     * @param model Gère les réponse au client.
     * @param view Tout se que le client voit.
     */
    public Controler(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    /**
     * Charge les cours dans le tableau d'après la session choisi.
     * @param session session a la qu'elle le client veut prendre son cours.
     */
    public void chargerListeCours(String session) {
        ObservableList<Course> listeObservalble = FXCollections.observableArrayList();
        ArrayList<Course> listeCours = model.demanderListeCours(session);
        listeObservalble.addAll(listeCours);
        view.getTableCours().setItems(listeObservalble);

    }

    /**
     * gère les information entré par le client pour leur inscription.
     * @param form formulaire que le client  doit remplir.
     */
    public void inscription(RegistrationForm form){
        String resultat = model.demandeInscription(form);
        if (resultat == null) {
            view.popUpReussi("Votre inscription au cours " + form.getCourse().getName() + " a été enregistré avec succès!" );
        } else {
            view.popUpErreur(resultat);
        }
    }
}
