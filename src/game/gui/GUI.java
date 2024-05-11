package game.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GUI extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Label l = new Label("Hello World!");
        root.getChildren().add(l);
    }
}
