package Client.clientFX;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
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
    private final int hauteur = 500;
    private final int largeur = 600;
    public static void run(String[] args) {
        View.launch(args);
    }
    public void start(Stage stage) {
        try {
            HBox racine = new HBox();
            VBox droit = new VBox();
            racine.setBackground(new Background(
                    new BackgroundFill(Color.rgb(245, 245, 220), CornerRadii.EMPTY, Insets.EMPTY)));


            Label message = new Label("Formulaire d'inscription");
            racine.getChildren().addAll(message, droit);
            Scene scene = new Scene(racine, largeur, hauteur);


            stage.setTitle("inscription UdeM");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
