package Client.clientFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.models.Course;
import server.models.RegistrationForm;

import java.util.ArrayList;


public class Controler {
    private Model model;
    private View view;

    public Controler(Model model, View view) {
        this.view = view;
        this.model = model;
    }
    public void chargerListeCours(String session) {
        ObservableList<Course> listeObservalble = FXCollections.observableArrayList();
        ArrayList<Course> listeCours = model.demanderListeCours(session);
        listeObservalble.addAll(listeCours);
        view.getTableCours().setItems(listeObservalble);

    }
    public void inscription(RegistrationForm form){
        String resultat = model.demandeInscription(form);
        if (resultat == null) {
            view.popUpReussi("Votre inscription au cours " + form.getCourse().getName() + " a été enregistré avec succès!" );
        } else {
            view.popUpErreur(resultat);
        }
    }
}
