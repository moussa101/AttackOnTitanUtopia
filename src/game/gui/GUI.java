package game.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GUI extends Application {
    public void start(Stage primaryStage) throws Exception {
// Step 1
        Group root = new Group();
        Label l = new Label("Hello World!");
        root.getChildren().add(l);
        Scene s = new Scene(root,600,600); // Step 2
        primaryStage.setScene(s); // Step 3
        primaryStage.setTitle("First FX Application");
        primaryStage.show(); } // Step 4
    public static void main (String[]args){
        launch(args); // Step 5
    } }
