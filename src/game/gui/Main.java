package game.gui;

import game.engine.Battle;
import game.engine.BattlePhase;
import game.engine.lanes.Lane;
import game.engine.weapons.Weapon;
import game.engine.weapons.factory.WeaponFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Stage primaryStage;
    private Stage BattleStage;
    private Stage GameOver;
    private Battle battle;
    private Stage weaponshop;
    private int Score;
    private int turns;
    private BattlePhase battlePhase;
    private int Resources;
    private ArrayList<Label> Info;
    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;
    private Stage WeaponStage;
    private final String[] weaponNames = {
            "Instant Kill Trap",
            "Wide Range Cannon",
            "Eagle Aim Cannon",
            "Heavy Duty Cannon"
    };

    private final String[] weaponTypes = {
            "Wall Trap",
            "VolleySpread Cannon",
            "Sniper Cannon",
            "Piercing Cannon"
    };



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Image imageIcon = new Image ("file:Icon.png");
        primaryStage.getIcons().add(imageIcon);
        primaryStage.setIconified(true);
        primaryStage.setIconified(false);
        Platform.setImplicitExit(false); // Prevents JavaFX from exiting when all stages are closed


        Image backgroundImage = new Image("file:pxArt.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(false);


        primaryStage.setTitle("Attack on Titan: Utopia");

        // Create buttons
        Button playButton = new Button("Play");
        Button instructionsButton = new Button("Instructions");
        Button exitButton = new Button("Exit");
        Button SettingsButton = new Button("Settings");

        // Set button actions
        playButton.setOnAction(event -> {
            System.out.println("Select difficulty mode...");
            showDifficultyOptions();
        });

        instructionsButton.setOnAction(event -> {
            InstructionMenu();
            System.out.println("Showing instructions...");
        });
        exitButton.setOnAction(actionEvent -> {
            System.out.println("Exiting...");
            Exit();
        });

        SettingsButton.setOnAction(actionEvent -> {
            SettingMenu();
            System.out.println("Showing settings...");
        });

        // Create layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(playButton, instructionsButton, SettingsButton, exitButton);

        // Combine background and layout
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(backgroundImageView,layout);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showDifficultyOptions() {
        Stage difficultyStage = new Stage();
        setIcon(difficultyStage);
        difficultyStage.setTitle("Select Difficulty Mode");

        // Create buttons for difficulty modes
        Button easyModeButton = new Button("Easy Mode");
        Button hardModeButton = new Button("Hard Mode");
        Button veryhardModeButton = new Button(" Very Hard Mode");

        // Set button actions
        easyModeButton.setOnAction(event -> {
            // Code to start the game in easy mode
            System.out.println("Starting game in Easy Mode...");
            difficultyStage.close();
            easyBattle();
        });

        veryhardModeButton.setOnAction(actionEvent -> {
            System.out.println("Starting game in Very Hard Mode...");
            difficultyStage.close();

        });

        hardModeButton.setOnAction(event -> {
            // Code to start the game in hard mode
            System.out.println("Starting game in Hard Mode...");
            difficultyStage.close();
            hardBattle();
        });

        // Create layout for difficulty selection
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(easyModeButton, hardModeButton, veryhardModeButton);

        Scene scene = new Scene(layout, 300, 200);
        difficultyStage.setScene(scene);
        difficultyStage.show();
    }

    private void showGameWindow(String difficulty) {
        Stage gameStage = new Stage();
        setIcon(gameStage);
        gameStage.setTitle("Attack on Titan: Utopia - " + difficulty + " Mode");

        // Add game content here...

        Scene scene = new Scene(new VBox(), 800, 600);
        gameStage.setScene(scene);
        gameStage.show();
    }

    private void Exit() {
        Stage Exit = new Stage();
        setIcon(Exit);
        Exit.setTitle("Exit");
        Label a = new Label("Are You Sure?");
        Button yesButton = new Button("Yes");

        Button noButton = new Button("No");

        yesButton.setOnAction(actionEvent -> {
            try {
                primaryStage.close();
            }
            catch (Exception e){

            }
            try {
                    BattleStage.close();
            }
            catch (Exception e){

            }
            try {
                GameOver.close();
            }
            catch (Exception e){

            }

            Exit.close();
        });
        noButton.setOnAction(actionEvent -> {
            Exit.close();
        });
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(a, yesButton, noButton);
        Scene scene = new Scene(layout, 300, 200);
        Exit.setScene(scene);
        Exit.show();
    }
    private void InstructionMenu() {
        Stage instructionsStage = new Stage();
        setIcon(instructionsStage);
        instructionsStage.setTitle("Instructions");

        String instructionsText = "Welcome to 'Attack on Titan: Utopia'! As the last line of defense against the titan onslaught, your mission is to protect the Utopia District and prevent the titans from breaching Wall Rose. You'll navigate through an endless battle across multiple lanes, each representing a section of the wall under attack. Deploy your arsenal of anti-titan weapons strategically to fend off the advancing titans. Keep a keen eye on the battlefield, monitoring the health of the walls, the approaching titans, and the resources at your disposal. Choose your actions wisely each turn, whether it's purchasing and deploying weapons or observing the titan movements. Defeating titans earns you valuable resources and increases your score. But beware, losing all wall sections in a lane marks it as lost, reducing your defensive capabilities. Stay vigilant and adapt to the changing battle phases as the intensity of the titan onslaught increases over time. Your ultimate goal is survival; how long can you withstand the titan onslaught and defend humanity's last bastion of hope?";

        Label l = new Label(instructionsText);
        l.setFont(new Font("Arial", 14));
        l.setWrapText(true);
        l.setAlignment(Pos.CENTER);
        l.setLineSpacing(5);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(l);
        layout.setAlignment(Pos.CENTER);

        layout.setPadding(new Insets(20));
        layout.setSpacing(30);

        Scene scene = new Scene(layout, 600, 400);
        instructionsStage.setScene(scene);
        instructionsStage.show();
    }
    private void setIcon(Stage stage) {
        Image icon = new Image("file:Icon.png");
        stage.getIcons().add(icon);
    }

    private void SettingMenu(){
        Stage Setting = new Stage();
        setIcon(Setting);
        Setting.setTitle("Settings");
        Label l = new Label("Undergoing");
        l.setFont(new Font("Arial", 14));
        l.setWrapText(true);
        l.setAlignment(Pos.CENTER);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(l);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 600, 400);
        Setting.setScene(scene);
        Setting.show();
    }
    private void easyBattle(){
        primaryStage.close();
        BattleStage = new Stage();
        setIcon(BattleStage);
        BattleStage.setTitle("Battle - Easy Mode");
        try {
            battle = new Battle(1,0,0,3,250);
        }
        catch (Exception e) {
            BattleStage.close();
            showErrorPopup("Game Initialization Error please restart or try uninstalling the game");
        }

        l1 = new Label("Current score :  "+battle.getScore());
        l2 = new Label("Current turn :  "+battle.getNumberOfTurns());
        l3 = new Label("Current phase :  "+battle.getBattlePhase());
        l4 = new Label("Current Resources :  "+battle.getResourcesGathered());
        Button b = new Button("Weapon shop");
        Button b1 = new Button("Exit game");
        Button passTurnbutton = new Button("Pass turn");
        passTurnbutton.setPrefWidth(120);


        b.setOnAction(actionEvent -> {
            WeaponShop();
        });


            passTurnbutton.setOnAction(actionEvent -> {
                passTurn();
            });

            b1.setOnAction(actionEvent -> {
                Exit();
            });




        AnchorPane mainlayout = new AnchorPane();
        HBox layout1 = new HBox(10);
        layout1.getChildren().addAll(l1, l2, l3, l4);
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setSpacing(40);
        mainlayout.getChildren().addAll(layout1);

        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(passTurnbutton,b, b1);
        layout2.setAlignment(Pos.BOTTOM_CENTER);
        mainlayout.getChildren().addAll(layout2);

        AnchorPane.setTopAnchor(layout1, 10.0);
        AnchorPane.setLeftAnchor(layout1, 0.0);
        AnchorPane.setRightAnchor(layout1, 0.0);

        // Anchor layout2 to the bottom and center
        AnchorPane.setBottomAnchor(layout2, 20.0);
        AnchorPane.setLeftAnchor(layout2, 390.0);

        // Creating and positioning WeaponSpace and TitanSpace
        for (int i = 0; i < 3; i++) {
            VBox weaponSpace = new VBox(10);
            Pane titanSpace = new Pane();
            titanSpace.setPrefSize(200, 400);

            AnchorPane.setTopAnchor(weaponSpace, 100.0);
            AnchorPane.setLeftAnchor(weaponSpace, 20.0 + i * 220); // Adjust as needed
            AnchorPane.setTopAnchor(titanSpace, 100.0);
            AnchorPane.setLeftAnchor(titanSpace, 20.0 + i * 220); // Adjust as needed

            mainlayout.getChildren().addAll(weaponSpace, titanSpace);
        }

        // Creating and positioning laneInfo VBox
        VBox laneInfo = new VBox(10);
        AnchorPane.setTopAnchor(laneInfo, 100.0);
        AnchorPane.setRightAnchor(laneInfo, 20.0);
        laneInfo.setSpacing(100);

        Info = new ArrayList<>();
        Label laneInfoLabel1 = new Label();
        Label laneInfoLabel2 = new Label();
        Label laneInfoLabel3 = new Label();

        Info.add(laneInfoLabel1);
        Info.add(laneInfoLabel2);
        Info.add(laneInfoLabel3);

        Object[] Lanes = battle.getLanes().toArray();

        for (int i = 0; i < battle.getLanes().size(); i++) {
                Lane s = (Lane)Lanes[i];
            Info.get(i).setStyle("-fx-font-size: 14px;");
            Info.get(i).setStyle("-fx-font-weight: bold;");
            Info.get(i).setStyle("-fx-font-style: italic;");
            Info.get(i).setText("Danger : "+s.getDangerLevel()+"\n"+"Health : "+s.getLaneWall().getCurrentHealth());
        }
        laneInfo.getChildren().addAll(Info);
        mainlayout.getChildren().add(laneInfo);

        Scene scene = new Scene(mainlayout, 1000, 800);
        BattleStage.setScene(scene);
        BattleStage.show();
    }
    private void passTurn(){
        battle.passTurn();
        Update();
    }
    private void GameOver(){
        BattleStage.close();
        try {
            WeaponStage.close();
        }
        catch (Exception e) {
        }
        Label f1 = new Label("Try Again ?");
        Button f2 = new Button("Yes");
        Button f3 = new Button("No");

        f2.setOnAction(actionEvent -> {
            start(primaryStage);
        });
        f3.setOnAction(actionEvent -> {
            Exit();
        });

        GameOver = new Stage();
        setIcon(GameOver);
        GameOver.setTitle("Game Over");

        Label l = new Label("Game Over");
        l.setFont(new Font("Arial", 20));
        l.setWrapText(true);
        l.setAlignment(Pos.CENTER);

        AnchorPane mainlayout = new AnchorPane();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(l,f1);
        layout.setAlignment(Pos.CENTER);
        mainlayout.getChildren().addAll(layout);
        HBox layout1 = new HBox(10);
        layout1.getChildren().addAll(f2,f3);
        layout1.setAlignment(Pos.BASELINE_CENTER);

        AnchorPane.setTopAnchor(layout, 150.0);
        AnchorPane.setRightAnchor(layout, 250.0);

        // Align the HBox to the right
        AnchorPane.setTopAnchor(layout1, 250.0);
        AnchorPane.setRightAnchor(layout1, 272.0);
        mainlayout.getChildren().addAll(layout1);
        Scene scene = new Scene(mainlayout, 600, 400);
        GameOver.setScene(scene);
        GameOver.show();
    }
    public void Update(){
       try{
           if (battle.getLanes().size() == 0){
               GameOver();
           }
       }
       catch (NullPointerException e){
           GameOver();
       }
        l1.setText("Current score :  "+battle.getScore());
        l2.setText("Current turn :  "+battle.getNumberOfTurns());
        l3.setText("Current phase :  "+battle.getBattlePhase());
        l4.setText("Current Resources :  "+battle.getResourcesGathered());

        Object[] Lanes = battle.getLanes().toArray();
        for (int i = 0; i < Info.size(); i++) {
            try{
                Lane s = (Lane)Lanes[i];
                if (!s.isLaneLost() && s!=null) {
                    Info.get(i).setText("Danger : " + s.getDangerLevel() + "\n" + "Health : " + s.getLaneWall().getCurrentHealth());
                }
                    else{
                        Info.get(i).setText("Danger : "+0+"\n"+"Health : "+0);
                    }

            }
            catch(Exception e){
                Info.get(i).setText("Danger : "+0+"\n"+"Health : "+0);
            }
        }

    }

    public void hardBattle(){
        BattleStage.setTitle("Battle - Hard Mode");



    }

    public void veryhardBattle(){

    }
    public void WeaponShop () {
        WeaponStage = new Stage();
        WeaponStage.setTitle("Weapon Store");

        // Create a GridPane to hold the four squares
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(5);
        root.setVgap(5);

        // Create four squares with mock data
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                GridPane square = createSquare();
                fillLabels(square, i * 2 + j);
                root.add(square, i, j);
            }
        }

        Scene scene = new Scene(root, 600, 400);
        WeaponStage.setScene(scene);
        WeaponStage.show();
    }

    private GridPane createSquare() {
        GridPane square = new GridPane();
        square.setPadding(new Insets(20));
        square.setHgap(10);
        square.setVgap(10);

        // Set background color to off-white
        javafx.scene.paint.Color offWhite = Color.rgb(245, 245, 245); // RGB values for off-white
        square.setBackground(new Background(new BackgroundFill(offWhite, CornerRadii.EMPTY, Insets.EMPTY)));

        // Add drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5); // Adjust the radius here (default is 10)
        square.setEffect(dropShadow);

        // Set border color to black
        square.setStyle("-fx-border-color: black;");

        // Create labels and button
        Label nameLabel = new Label("Name:");
        Label typeLabel = new Label("Type:");
        Label priceLabel = new Label("Price:");
        Label damageLabel = new Label("Damage:");

        // Add labels and button to the square
        square.add(nameLabel, 0, 0);
        square.add(typeLabel, 0, 1);
        square.add(priceLabel, 0, 2);
        square.add(damageLabel, 0, 3);

        Button purchaseButton = new Button("Purchase");
        // Move purchase button up and to the left
        square.add(purchaseButton, 0, 4);
        GridPane.setMargin(purchaseButton, new Insets(-10, 0, 0, -10));

        // Set square size to fit the screen
        square.setMinSize(250, 150);
        square.setMaxSize(250, 150);

        // Set event handler for purchase button
        purchaseButton.setOnAction(e -> openLaneScene());

        return square;
    }

    private void fillLabels(GridPane square, int index) {
        // Get labels from the square
        Label nameLabel = (Label) square.getChildren().get(0);
        Label typeLabel = (Label) square.getChildren().get(1);

        // Set weapon name and type from arrays
        nameLabel.setText(weaponNames[index]);
        typeLabel.setText(weaponTypes[index]);
    }

    private void openLaneScene() {
        Stage laneStage = new Stage();
        VBox laneRoot = new VBox(10);
        laneRoot.setPadding(new Insets(10));

        Button easyModeButton = new Button("Easy Mode");
        Button hardModeButton = new Button("Hard Mode");

        easyModeButton.setOnAction(e -> openLaneLevelScene(3));
        hardModeButton.setOnAction(e -> openLaneLevelScene(5));

        laneRoot.getChildren().addAll(easyModeButton, hardModeButton);

        Scene laneScene = new Scene(laneRoot, 300, 100);
        laneStage.setScene(laneScene);
        laneStage.setTitle("Lane Selection");
        laneStage.show();
    }

    private void openLaneLevelScene(int numLanes) {
        Stage laneLevelStage = new Stage();
        VBox laneLevelRoot = new VBox(10);
        laneLevelRoot.setPadding(new Insets(10));

        for (int i = 1; i <= numLanes; i++) {
            Button laneButton = new Button("Lane " + i);
            laneLevelRoot.getChildren().add(laneButton);
        }

        int height = numLanes > 3 ? numLanes * 40  : 110;
        Scene laneLevelScene = new Scene(laneLevelRoot, 100, height);
        laneLevelStage.setScene(laneLevelScene);
        laneLevelStage.setTitle("Lane Level Selection");
        laneLevelStage.show();
    }

    public static void showErrorPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void handle(ActionEvent actionEvent) {

    }
}

