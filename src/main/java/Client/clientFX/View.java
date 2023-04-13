package Client.clientFX;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import server.models.Course;


public class View extends Application {
    private final Controler controller = new Controler(new Model(), this);
    private final int hauteur = 500;
    private final int largeur = 600;
    public static void run(String[] args) {
        View.launch(args);
    }
    public void start(Stage stage) {
        try {
            HBox racine = new HBox();
            VBox droit = new VBox();
            VBox gauche = new VBox();
            racine.setBackground(new Background(
                    new BackgroundFill(Color.rgb(245, 245, 220), CornerRadii.EMPTY, Insets.EMPTY)));
            HBox petiteBoite = new HBox();


    // Boite de Gauche
            racine.getChildren().add(gauche);
            gauche.setPrefWidth(300);
            droit.setAlignment(Pos.TOP_CENTER);
            gauche.setPadding(new Insets(5, 5, 5, 5));

            // Titre liste des cours
            Label listeDesCours = new Label("Liste des cours");
            gauche.getChildren().add(listeDesCours);
            listeDesCours.setFont(new Font("Arial", 16));
            listeDesCours.setPadding(new Insets(7,7,20,7));



            // TableView pour les cours
            TableView<Course> tableCours = new TableView<>();
            TableColumn<Course, String> code = new TableColumn("Code");
            TableColumn<Course, String> cours = new TableColumn("Cours");
            cours.setPrefWidth(150);
            tableCours.getColumns().addAll(code,cours);
            tableCours.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            gauche.getChildren().add(tableCours);

            // Petite boite
            gauche.getChildren().add(petiteBoite);
            petiteBoite.setPrefHeight(100);
            petiteBoite.setSpacing(40);


            // menu de session
            VBox menu = new VBox();
            petiteBoite.getChildren().add(menu);
            ComboBox<String> saisonComboBox = new ComboBox<>();
            saisonComboBox.getItems().addAll("Hiver", "Été", "Automne");
            saisonComboBox.setValue("Hiver");
            saisonComboBox.setPrefWidth(100);
            menu.setAlignment(Pos.CENTER);
            menu.getChildren().add(saisonComboBox);



            // Bouton charger
            VBox boutonCharger = new VBox();
            Button charger = new Button("charger");
            charger.setPrefWidth(75);
            boutonCharger.setAlignment(Pos.CENTER_LEFT);
            boutonCharger.getChildren().add(charger);
            petiteBoite.getChildren().add(boutonCharger);

            charger.setOnAction( e-> {
               tableCours.getItems().clear();




            });



            // Separateur
            Separator separateur = new Separator(Orientation.VERTICAL);
            separateur.setStyle("-fx-background-color: white;");
            racine.getChildren().add(separateur);

            // Boite de droite
            racine.getChildren().add(droit);
            droit.setPrefWidth(300);
            gauche.setAlignment(Pos.TOP_CENTER);
            droit.setPadding(new Insets(10, 10, 10, 10));
            droit.setSpacing(20);

            // Titre formulaire
            Label FormulaireInscription = new Label("Formulaire d'inscription");
            droit.getChildren().add(FormulaireInscription);
            FormulaireInscription.setFont(new Font("Arial", 16));
            FormulaireInscription.setPadding(new Insets(7,7,20,7));

            // Prénom
            TextField prenom = new TextField();
            prenom.setPromptText("Prénom");
            prenom.setPadding(new Insets(5,5,20,5));
            droit.getChildren().add(prenom);
            // nom
            TextField nom = new TextField();
            nom.setPromptText("Nom");
            nom.setPadding(new Insets(5,5,20,5));
            droit.getChildren().add(nom);
            //email
            TextField email = new TextField();
            email.setPromptText("Courriel");
            email.setPadding(new Insets(5,5,20,5));
            droit.getChildren().add(email);
            //matricule
            TextField matricule = new TextField();
            matricule.setPromptText("Matricule");
            matricule.setPadding(new Insets(5,5,20,5));
            droit.getChildren().add(matricule);



            // Créer un bouton avec un texte
            Button bouton = new Button("envoyer");
            bouton.setAlignment(Pos.BOTTOM_CENTER);
            droit.getChildren().add(bouton);









            Scene scene = new Scene(racine, largeur, hauteur);
            stage.setTitle("inscription UdeM");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
