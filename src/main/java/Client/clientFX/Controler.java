package Client.clientFX;

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
        ArrayList<Course> listeCours = model.demanderListeCours(session);

    }
    public void inscription(RegistrationForm form){
        
    }
}
