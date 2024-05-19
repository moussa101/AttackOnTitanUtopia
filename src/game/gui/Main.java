package game.gui;

import game.engine.Battle;
import game.engine.BattlePhase;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.*;
import javafx.application.Application;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static javafx.application.Application.launch;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Stage primaryStage;
    private Stage BattleStage;
    private Stage GameOver;
    private VBox settingsPane;
    private Battle battle;
    private Diffculty Difficulty = Diffculty.EASY;
    private Stage laneLevelStage;
    private Label HighScoreLabel;
    private static final String HIGHSCORE_FILE = "highscore.txt";;
    private int Score;
    private ArrayList<Label> Info;
    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;
    private Stage WeaponStage;
    private Label nameLabel;
    private Label typeLabel;
    private Label priceLabel;
    private Label damageLabel;
    private final String[] weaponNames = {
            "Heavy Duty Cannon",
            "Wide Range Cannon",
            "Eagle Aim Cannon",
            "Instant Kill Trap"

    };

    private final String[] weaponTypes = {
            "Wall Trap",
            "VolleySpread Cannon",
            "Sniper Cannon",
            "Piercing Cannon"
    };
    private VBox[] weaponSpaces;
    private StackPane[] titanSpaces;
    private int resetCount =0;
    private boolean Cheats = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setIcon(primaryStage);
        primaryStage.setIconified(true);
        primaryStage.setIconified(false);

        Image backgroundImage = new Image("file:AOT%20Images/Gicon&Start/pxArt.png");
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

    private void createImageButton(VBox weaponSpace, String imagePath) {
        // Create a button
        Button button = new Button();

        // Load the image
        Image image = new Image(imagePath);

        // Create an image view with the loaded image
        ImageView imageView = new ImageView(image);

        // Set the size of the image view
        imageView.setFitWidth(30); // Adjust width as needed
        imageView.setFitHeight(30); // Adjust height as needed

        // Set the image view as the graphic content of the button
        button.setGraphic(imageView);

        weaponSpace.getChildren().add(button);
    }
    private void insertImageToTitanStackPane(StackPane titanSpace, String imagePath, double x) {
        // Load the image
        Image image = new Image(imagePath);

        // Create an image view with the loaded image
        ImageView imageView = new ImageView(image);

        // Set the size of the image view
        imageView.setFitWidth(40); // Adjust width as needed
        imageView.setFitHeight(60); // Adjust height as needed

        // Generate a random y-coordinate within the height of the StackPane
        double randomY = (Math.random() * titanSpace.getHeight())-150;

        x = Math.min(x,800);
        // Set the position of the image view within the StackPane
        StackPane.setMargin(imageView, new Insets(randomY, 0, 0, x-130)); // Adjust margins as needed

        // Add the image view to the StackPane
        titanSpace.getChildren().add(imageView);
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
            Difficulty = Difficulty.EASY;
        });

        veryhardModeButton.setOnAction(actionEvent -> {
            System.out.println("Starting game in Very Hard Mode...");
            difficultyStage.close();
            veryhardBattle();
            Difficulty = Difficulty.VERYHARD;
        });

        hardModeButton.setOnAction(event -> {
            // Code to start the game in hard mode
            System.out.println("Starting game in Hard Mode...");
            difficultyStage.close();
            hardBattle();
            Difficulty = Difficulty.HARD;
        });

        // Create layout for difficulty selection
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(easyModeButton, hardModeButton, veryhardModeButton);

        Scene scene = new Scene(layout, 300, 200);
        difficultyStage.setScene(scene);
        difficultyStage.show();
    }


    private void Exit() {
        Stage Exit = new Stage();
        setIcon(Exit);
        Exit.setTitle("Exit");
        Label a = new Label("Are You Sure?");
        Button yesButton = new Button("Yes");

        Button noButton = new Button("No");

        yesButton.setOnAction(actionEvent -> {
            closeAll();
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
        String encodedPath = "file:AOT%20Images/Gicon&Start/Icon.png";
        Image icon = new Image(encodedPath);
        stage.getIcons().add(icon);
    }

    private void SettingMenu(){
        int count = 0;
        Stage Setting = new Stage();
        setIcon(Setting);
        Setting.setTitle("Settings");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Button Reset = new Button("Reset HighScore");
        Reset.setOnAction(actionEvent -> {
            resetHighScore();
        });
        layout.getChildren().addAll(Reset);
        Scene scene = new Scene(layout, 300, 200);
        Setting.setScene(scene);
        Setting.show();
    }

    private void resetHighScore() {
        resetCount++;
        if (resetCount % 5 == 0) {
            Cheats = !Cheats;
            String message = Cheats ? "Cheats have been enabled" : "Cheats have been disabled";
            showPopUp("Cheats", message);
        } else {
            saveHighscore(0);
        }
    }
    private void ReturntoMainMenu(){


        Stage Exit = new Stage();
        setIcon(Exit);
        Exit.setTitle(" Return to Main Menu");
        Label a = new Label("Are You Sure?");
        Label B = new Label("You will lose all your resources");
        a.setFont(new Font("Arial", 16));
        B.setFont(new Font("Arial", 13));

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(actionEvent -> {
            closeAll();
            start(primaryStage);
            Exit.close();
        });
        noButton.setOnAction(actionEvent -> {
            Exit.close();
        });
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        HBox h = new HBox(10);
        h.getChildren().addAll(yesButton,noButton);
            h.setAlignment(Pos.CENTER);
            AnchorPane.setLeftAnchor(h,52.5);
        layout.getChildren().addAll(a,B,h);
        Scene scene = new Scene(layout, 300, 200);
        Exit.setScene(scene);
        Exit.show();
    }
    private void easyBattle(){
        primaryStage.close();
        BattleStage = new Stage();
        setIcon(BattleStage);
        BattleStage.setTitle("Battle - Easy Mode");

        if (Cheats) {
            try {
                battle = new Battle(1, 0, 800, 3, 9999999);

            } catch (Exception e) {
                BattleStage.close();
                showError("Game Initialization Error please restart or try uninstalling the game");
            }
        }
        else {
            try {
                battle = new Battle(1, 0, 800, 3, 250);

            } catch (Exception e) {
                BattleStage.close();
                showError("Game Initialization Error please restart or try uninstalling the game");
            }
        }


        l1 = new Label("Current score :  "+battle.getScore());
        l2 = new Label("Current turn :  "+battle.getNumberOfTurns());
        l3 = new Label("Current phase :  "+battle.getBattlePhase());
        l4 = new Label("Current Resources :  "+battle.getResourcesGathered());
        HighScoreLabel = new Label("High Score : "+loadHighscore());
        HighScoreLabel.getStyleClass().add("label-hs");
        l1.getStyleClass().add("label-cs");

        Button b = new Button("Weapon shop");
        Button b1 = new Button("Exit game");
        Button passTurnbutton = new Button("Pass turn");
        Button AI = new Button("AI");
        Button Return = new Button("Return to main menu");
        passTurnbutton.setPrefWidth(100);

        Return.setOnAction(actionEvent -> {
            ReturntoMainMenu();
        });
        AI.setOnAction(actionEvent -> {
            AI();
        });

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
        layout1.getChildren().addAll(l1, l2, l3, l4, HighScoreLabel);
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setSpacing(40);
        mainlayout.getChildren().addAll(layout1);

        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(passTurnbutton,b,AI,Return);
        layout2.setAlignment(Pos.BOTTOM_CENTER);
        mainlayout.getChildren().addAll(layout2);

        AnchorPane.setTopAnchor(layout1, 10.0);
        AnchorPane.setLeftAnchor(layout1, 0.0);
        AnchorPane.setRightAnchor(layout1, 0.0);

        // Anchor layout2 to the bottom and center
        AnchorPane.setBottomAnchor(layout2, 20.0);
        AnchorPane.setLeftAnchor(layout2, 310.0);

        // Creating and positioning WeaponSpace and TitanSpace
        weaponSpaces = new VBox[getNumLanes()];
        titanSpaces = new StackPane[getNumLanes()];

        double verticalGap = 50; // Adjust the vertical gap between each VBox


        for (int i = 0; i < 3; i++) {
            // Create VBox and Pane instances
            VBox weaponSpace = new VBox(10);
            Pane titanSpace = new StackPane();
            titanSpace.setPrefSize(200, 400);
            titanSpace.setPadding(new Insets(20));

            // Add VBox instances to the HBox

            // Add VBox and Pane instances to arrays
            weaponSpaces[i] = weaponSpace;
            titanSpaces[i] = (StackPane) titanSpace;
        }


        double space = 30;
        double increment = 30;
// Add each StackPane from titanSpaces array to the main layout
        for (StackPane titanSpace : titanSpaces) {
            mainlayout.getChildren().add(titanSpace);
            AnchorPane.setLeftAnchor(titanSpace, 40.0);
            AnchorPane.setTopAnchor(titanSpace, space);
            space += increment;
        }
        space = 50;
        increment = 150;

          // Set the desired space increment
        for (VBox box : weaponSpaces) {
            box.setMaxSize(150, 150);
            box.setMinSize(150, 150);
            AnchorPane.setLeftAnchor(box, 40.0);
            AnchorPane.setTopAnchor(box, space);
            mainlayout.getChildren().add(box);
            space += increment;  // Increment the space by the fixed value
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
            Info.get(i).setText("Danger : "+s.getDangerLevel()+"\n"+"Health : "+s.getLaneWall().getCurrentHealth()+"\n"+"Number of Titans : "+s.getTitans().size());
        }
        laneInfo.getChildren().addAll(Info);
        mainlayout.getChildren().add(laneInfo);

        Scene scene = new Scene(mainlayout, 1000, 800);


        BattleStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("easymode.css").toExternalForm());
        BattleStage.show();
    }
    public void hardBattle(){
        primaryStage.close();
        BattleStage = new Stage();
        setIcon(BattleStage);
        BattleStage.setTitle("Battle - Hard Mode");

        if (Cheats) {
            try {
                battle = new Battle(1, 0, 800, 5, 9999999);

            } catch (Exception e) {
                BattleStage.close();
                showError("Game Initialization Error please restart or try uninstalling the game");
            }
        }
        else {
            try {
                battle = new Battle(1, 0, 800, 5, 125);

            } catch (Exception e) {
                BattleStage.close();
                showError("Game Initialization Error please restart or try uninstalling the game");
            }
        }

        l1 = new Label("Current score :  "+battle.getScore());
        l2 = new Label("Current turn :  "+battle.getNumberOfTurns());
        l3 = new Label("Current phase :  "+battle.getBattlePhase());
        l4 = new Label("Current Resources :  "+battle.getResourcesGathered());
        HighScoreLabel = new Label("High Score : "+loadHighscore());
        HighScoreLabel.getStyleClass().add("label-hs");
        l1.getStyleClass().add("label-cs");

        Button b = new Button("Weapon shop");
        Button b1 = new Button("Exit game");
        Button passTurnbutton = new Button("Pass turn");
        Button AI = new Button("AI");
        Button Return = new Button("Return to main menu");
        passTurnbutton.setPrefWidth(120);

        Return.setOnAction(actionEvent -> {
            ReturntoMainMenu();
        });

        AI.setOnAction(actionEvent -> {
            AI();
        });

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
        layout1.getChildren().addAll(l1, l2, l3, l4, HighScoreLabel);
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setSpacing(40);
        mainlayout.getChildren().addAll(layout1);

        HBox layout2 = new HBox(10);
        layout2.getChildren().addAll(passTurnbutton,b,AI,Return);
        layout2.setAlignment(Pos.BOTTOM_CENTER);
        mainlayout.getChildren().addAll(layout2);

        AnchorPane.setTopAnchor(layout1, 10.0);
        AnchorPane.setLeftAnchor(layout1, 0.0);
        AnchorPane.setRightAnchor(layout1, 0.0);

        // Anchor layout2 to the bottom and center
        AnchorPane.setBottomAnchor(layout2, 20.0);
        AnchorPane.setLeftAnchor(layout2, 310.0);

        // Creating and positioning WeaponSpace and TitanSpace
        weaponSpaces = new VBox[5];
        titanSpaces = new StackPane[5];

        double verticalGap = 50; // Adjust the vertical gap between each VBox


        for (int i = 0; i < 5; i++) {
            // Create VBox and Pane instances
            VBox weaponSpace = new VBox(10);
            Pane titanSpace = new StackPane();
            titanSpace.setPrefSize(200, 400);
            titanSpace.setPadding(new Insets(20));

            // Add VBox instances to the HBox

            // Add VBox and Pane instances to arrays
            weaponSpaces[i] = weaponSpace;
            titanSpaces[i] = (StackPane)titanSpace;
        }

// Add each StackPane from titanSpaces array to the main layout
        double space = 30;
        double increment = 30;
// Add each StackPane from titanSpaces array to the main layout
        for (StackPane titanSpace : titanSpaces) {
            mainlayout.getChildren().add(titanSpace);
            AnchorPane.setLeftAnchor(titanSpace, 40.0);
            AnchorPane.setTopAnchor(titanSpace, space);
            space += increment;
        }
        space = 50;
        increment = 70;

        for (VBox box : weaponSpaces) {
            box.setMaxSize(150, 150);
            box.setMinSize(150, 150);
            AnchorPane.setLeftAnchor(box, 40.0);
            AnchorPane.setTopAnchor(box, space);
            mainlayout.getChildren().add(box);
            space += increment;  // Increment the space by the fixed value
        }



        // Creating and positioning laneInfo VBox
        VBox laneInfo = new VBox(10);
        AnchorPane.setTopAnchor(laneInfo, 100.0);
        AnchorPane.setRightAnchor(laneInfo, 20.0);
        laneInfo.setSpacing(50);

        Info = new ArrayList<>();
        for (int i = 0; i <battle.getOriginalLanes().size(); i++) {
            Label a = new Label();
            Info.add(a);
        }

        Object[] Lanes = battle.getLanes().toArray();

        for (int i = 0; i < battle.getLanes().size(); i++) {
            Lane s = (Lane)Lanes[i];
            Info.get(i).setText("Danger : "+s.getDangerLevel()+"\n"+"Health : "+s.getLaneWall().getCurrentHealth()+"\n"+"Number of Titans : "+s.getTitans().size());
        }
        laneInfo.getChildren().addAll(Info);
        mainlayout.getChildren().add(laneInfo);

        Scene scene = new Scene(mainlayout, 1000, 800);


        BattleStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("hardmode.css").toExternalForm());
        BattleStage.show();

    }

    public void veryhardBattle(){
            primaryStage.close();
            BattleStage = new Stage();
            setIcon(BattleStage);
            BattleStage.setTitle("Battle - Very Hard");

            if (Cheats) {
                try {
                    battle = new Battle(1, 0, 800, 7, 75);
                    showPopUp("Smart Ass", "No Cheats in Vey Hard mode ;)");

                } catch (Exception e) {
                    BattleStage.close();
                    showError("Game Initialization Error please restart or try uninstalling the game");
                }
            }
            else {
                try {
                    battle = new Battle(1, 0, 800, 7, 75);

                } catch (Exception e) {
                    BattleStage.close();
                    showError("Game Initialization Error please restart or try uninstalling the game");
                }
            }

            l1 = new Label("Current score :  "+battle.getScore());
            l2 = new Label("Current turn :  "+battle.getNumberOfTurns());
            l3 = new Label("Current phase :  "+battle.getBattlePhase());
            l4 = new Label("Current Resources :  "+battle.getResourcesGathered());
            HighScoreLabel = new Label("High Score : "+loadHighscore());
            HighScoreLabel.getStyleClass().add("label-hs");
            l1.getStyleClass().add("label-cs");

            Button b = new Button("Weapon shop");
            Button b1 = new Button("Exit game");
            Button passTurnbutton = new Button("Pass turn");
            Button Return = new Button("Return to main menu");
            passTurnbutton.setPrefWidth(120);


            b.setOnAction(actionEvent -> {
                WeaponShop();
            });

            Return.setOnAction(actionEvent -> {
                ReturntoMainMenu();
            });


            passTurnbutton.setOnAction(actionEvent -> {
                passTurn();
            });

            b1.setOnAction(actionEvent -> {
                Exit();
            });




            AnchorPane mainlayout = new AnchorPane();
            HBox layout1 = new HBox(10);
            layout1.getChildren().addAll(l1, l2, l3, l4, HighScoreLabel);
            layout1.setAlignment(Pos.TOP_CENTER);
            layout1.setSpacing(40);
            mainlayout.getChildren().addAll(layout1);

            HBox layout2 = new HBox(10);
            layout2.getChildren().addAll(passTurnbutton,b,Return);
            layout2.setAlignment(Pos.BOTTOM_CENTER);
            mainlayout.getChildren().addAll(layout2);

            AnchorPane.setTopAnchor(layout1, 10.0);
            AnchorPane.setLeftAnchor(layout1, 0.0);
            AnchorPane.setRightAnchor(layout1, 0.0);

            // Anchor layout2 to the bottom and center
            AnchorPane.setBottomAnchor(layout2, 20.0);
            AnchorPane.setLeftAnchor(layout2, 310.0);

            // Creating and positioning WeaponSpace and TitanSpace
            weaponSpaces = new VBox[7];
            titanSpaces = new StackPane[7];

            double verticalGap = 50; // Adjust the vertical gap between each VBox


            for (int i = 0; i < 7; i++) {
                // Create VBox and Pane instances
                VBox weaponSpace = new VBox(10);
                Pane titanSpace = new StackPane();
                titanSpace.setPrefSize(200, 400);
                titanSpace.setPadding(new Insets(20));

                // Add VBox instances to the HBox

                // Add VBox and Pane instances to arrays
                weaponSpaces[i] = weaponSpace;
                titanSpaces[i] = (StackPane)titanSpace;
            }

// Add each StackPane from titanSpaces array to the main layout
            double space = 30;
            double increment = 30;
// Add each StackPane from titanSpaces array to the main layout
            for (StackPane titanSpace : titanSpaces) {
                mainlayout.getChildren().add(titanSpace);
                AnchorPane.setLeftAnchor(titanSpace, 40.0);
                AnchorPane.setTopAnchor(titanSpace, space);
                space += increment;
            }
            space = 50;
            increment = 100;

            for (VBox box : weaponSpaces) {
                box.setMaxSize(150, 150);
                box.setMinSize(150, 150);
                AnchorPane.setLeftAnchor(box, 40.0);
                AnchorPane.setTopAnchor(box, space);
                mainlayout.getChildren().add(box);
                space += increment;  // Increment the space by the fixed value
            }



            // Creating and positioning laneInfo VBox
            VBox laneInfo = new VBox(10);
            AnchorPane.setTopAnchor(laneInfo, 100.0);
            AnchorPane.setRightAnchor(laneInfo, 20.0);
            laneInfo.setSpacing(25);

            Info = new ArrayList<>();
            for (int i = 0; i <battle.getOriginalLanes().size(); i++) {
                Label a = new Label();
                Info.add(a);
            }

            Object[] Lanes = battle.getLanes().toArray();

            for (int i = 0; i < battle.getLanes().size(); i++) {
                Lane s = (Lane)Lanes[i];
                Info.get(i).setText("Danger : "+s.getDangerLevel()+"\n"+"Health : "+s.getLaneWall().getCurrentHealth()+"\n"+"Number of Titans : "+s.getTitans().size());
            }
            laneInfo.getChildren().addAll(Info);
            mainlayout.getChildren().add(laneInfo);

            Scene scene = new Scene(mainlayout, 1000, 800);


            BattleStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("veryhardmode.css").toExternalForm());
            BattleStage.show();

    }

    private void passTurn(){
        try {
            battle.passTurn();
            Update();
        }
        catch (Exception e) {
            GameOver();
        }


    }
    public void AI() {
        Update();
        boolean b = false;
        int[] weaponPriorities = {4, 2, 1, 3};

        for (int priority : weaponPriorities) {
            if (battle.getLanes().isEmpty()) {
                GameOver();
                return;
            }

            Lane lastElement = null;
            for (Lane element : battle.getLanes()) {
                lastElement = element;
            }

            try {
                battle.purchaseWeapon(priority, lastElement);
                Update();
                b = true;
                return; // Exit the method if a weapon is successfully purchased
            } catch (InsufficientResourcesException | InvalidLaneException | NullPointerException e) {

            }
        }
        if (!b){
            passTurn();
        }

        Update();
    }

    private void GameOver() {
        Stage GameOver = new Stage();
        setIcon(GameOver);
        GameOver.setTitle("Game Over");

        try {
            BattleStage.close();
            if (!(loadHighscore() >= Score)) {
                saveHighscore(Score);
            }
        } catch (Exception e) {

        }

        try {
            WeaponStage.close();
        } catch (Exception e) {

        }

        Label f1 = new Label("Try Again ?");
        Button f2 = new Button("Yes");
        Button f3 = new Button("No");

        f2.setOnAction(actionEvent -> {
            start(primaryStage);
        });
        f3.setOnAction(actionEvent -> {
            GameOver.close();
        });

        Label l = new Label("Game Over");
        l.getStyleClass().add("label-s1");
        l.setWrapText(true);
        l.setAlignment(Pos.CENTER);

        Label highScore = new Label("High Score : " + loadHighscore());
        highScore.getStyleClass().add("label-s2");


        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(l, highScore, f1);
        vbox.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(f2, f3);
        hbox.setAlignment(Pos.CENTER);

        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(vbox, hbox);

        StackPane centerPane = new StackPane(mainLayout);
        centerPane.setAlignment(Pos.CENTER);

        AnchorPane root = new AnchorPane(centerPane);
        AnchorPane.setTopAnchor(centerPane, 0.0);
        AnchorPane.setRightAnchor(centerPane, 0.0);
        AnchorPane.setBottomAnchor(centerPane, 0.0);
        AnchorPane.setLeftAnchor(centerPane, 0.0);

        Scene scene = new Scene(root, 600, 400);
        GameOver.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("gameover.css").toExternalForm());
        l.setFont(new Font("Arial", 40));
        GameOver.show();
    }

    public void Update() {
        try {
            if (battle.isGameOver()) {
                GameOver();
            }
        } catch (NullPointerException e) {
            GameOver();
        }
        Score = battle.getScore();
        l1.setText("Current score :  " + battle.getScore());
        l2.setText("Current turn :  " + battle.getNumberOfTurns());
        l3.setText("Current phase :  " + battle.getBattlePhase());
        l4.setText("Current Resources :  " + battle.getResourcesGathered());

        if (Score > loadHighscore()) {
            saveHighscore(Score);
            HighScoreLabel.setText("High Score : "+loadHighscore());
        }

        Object[] Lanes = battle.getOriginalLanes().toArray();

        for (int i = 0; i < Info.size(); i++) {
            try {
                Lane s = (Lane)Lanes[i];
                if (!s.isLaneLost()){
                    Info.get(i).setText("Danger : "+s.getDangerLevel()+"\n"+"Health : "+s.getLaneWall().getCurrentHealth()+"\n"+"Number of Titans : "+s.getTitans().size());
                }
                else {
                    Info.get(i).setText("DEFEATED");
                }
            }
            catch (NullPointerException e) {
                Info.get(i).setText("DEFEATED");
            }


        }
       for (VBox v : weaponSpaces){
           v.getChildren().clear();
       }
       for (StackPane s : titanSpaces){
           s.getChildren().clear();
       }

        for (int i = 0; i <battle.getOriginalLanes().size() ; i++) {
            if (!battle.getOriginalLanes().get(i).isLaneLost()){
                for(Titan t :battle.getOriginalLanes().get(i).getTitans()){
                    t.setSpeed(t.getSpeed()*2);
                }
               for(Titan t : battle.getOriginalLanes().get(i).getTitans()){
                   if (t instanceof PureTitan){
                       insertImageToTitanStackPane(titanSpaces[i],"file:AOT%20Images/Titans/3t.png",t.getDistance());
                   }
                   else if (t instanceof AbnormalTitan){
                       insertImageToTitanStackPane(titanSpaces[i],"file:AOT%20Images/Titans/2t.png",t.getDistance());

                   }
                   else if (t instanceof ArmoredTitan){
                       insertImageToTitanStackPane(titanSpaces[i],"file:AOT%20Images/Titans/4t.png",t.getDistance());

                   }
                   else if (t instanceof ColossalTitan){
                       insertImageToTitanStackPane(titanSpaces[i],"file:AOT%20Images/Titans/1t.png",t.getDistance());
                   }

               }
               for( Weapon w : battle.getOriginalLanes().get(i).getWeapons()){

                   if (w instanceof PiercingCannon){
                       createImageButton(weaponSpaces[i],"file:AOT%20Images/Weapons/pier.jpeg");
                   } else if (w instanceof SniperCannon) {
                       createImageButton(weaponSpaces[i],"file:AOT%20Images/Weapons/eagle.jpeg");
                   } else if (w instanceof VolleySpreadCannon) {
                       createImageButton(weaponSpaces[i],"file:AOT%20Images/Weapons/vs.jpeg");

                   } else if (w instanceof WallTrap) {
                       createImageButton(weaponSpaces[i],"file:AOT%20Images/Weapons/dead.jpeg");
                   }

               }

            }
        }
        if (loadHighscore()==3000){
            EasterEgg();
            saveHighscore(loadHighscore()+1);
        }
        if (battle.getOriginalLanes().size()==7&& battle.getScore()==1000){
            SecretEnding();
        }

    }


    private void SecretEnding() {
        closeAll(); // Assuming closeAll is implemented elsewhere
        Stage secretStage = new Stage();
        setIcon(secretStage); // Assuming setIcon is implemented elsewhere
        secretStage.setTitle("Secret Ending");

        // Morse code message formatted as a paragraph
        String mcode =
                "- .... .- -. -.- ... / ..-. --- .-. / .--. .-.. .- -.-- .. -. --. / - .... . / --. .- -- . / \n" +
                        ".. / .... --- .--. . / -.-- --- ..- / . -. .--- --- -.-- . -.. / .. / -.- -. --- .-- / - .... .- - / \n" +
                        ".. - / -.-. --- -. - .- .. -. . -.. / -- .- -. -.-- / -... ..- --. ... / -- .- -.-- -... . / .. -. / \n" +
                        "- .... . / ..-. ..- - ..- .-. . / .-- . / -.-. .- -. / ..-. .. -..- / .. - / - .. .-.. .-.. / - .... . -. / \n" +
                        "--. --- --- -.. -... -.-- . / -- -.-- / ..-. .-. .. . -. -.. / .- -. -.. / -.-- --- ..- / ... .... --- ..- .-.. -.. / \n" +
                        ".--. .-. --- -... .- -... .-.. -.-- / - .- -.- . / .- / ... .... --- .-- . .-.";

        Label endLabel = new Label(mcode);

        // Button to copy the Morse code to clipboard
        Button copyButton = new Button("CopyCode");
        copyButton.setOnAction(event -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(mcode);
            clipboard.setContent(content);
        });

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(endLabel, copyButton);

        Scene scene = new Scene(vbox, 600, 400);
        secretStage.setScene(scene);
        secretStage.show();
    }



    private void WeaponShop() {
        WeaponStage = new Stage();
        WeaponStage.setTitle("Weapon Store");
        setIcon(WeaponStage);

        // Create a GridPane to hold the four squares
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(50);
        root.setVgap(50);

        // Create four squares with mock data
        StackPane mainLayout = new StackPane();

        GridPane Purchasebox = new GridPane();
        Purchasebox.setHgap(250);
        Purchasebox.setVgap(250);
        Purchasebox.setAlignment(Pos.BOTTOM_CENTER);

        Purchasebox.setPadding(new Insets(10));


        // Create purchase buttons
        Button purchaseButton1 = createPurchaseButton("Purchase", 1);
        Button purchaseButton2 = createPurchaseButton("Purchase", 2);
        Button purchaseButton3 = createPurchaseButton("Purchase", 3);
        Button purchaseButton4 = createPurchaseButton("Purchase", 4);


        // Add purchase buttons to Purchasebox
        Purchasebox.add(purchaseButton1, 0, 0);
        Purchasebox.add(purchaseButton2, 1, 0);
        Purchasebox.add(purchaseButton3, 0, 1);
        Purchasebox.add(purchaseButton4, 1, 1);


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                GridPane square = createSquare();
                fillLabels(square, i * 2 + j);
                root.add(square, i, j);
            }
        }
        root.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(root, Purchasebox);

        Scene scene = new Scene(mainLayout, 700, 500);
        WeaponStage.setScene(scene);
        WeaponStage.show();
    }

    // Create a purchase button with the given text and index
    private Button createPurchaseButton(String text, int index) {
        Button purchaseButton = new Button(text);
        purchaseButton.setOnAction(e -> openLaneLevelScene(index));
        return purchaseButton;
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

        // Create labels
         nameLabel = new Label("Name:");
         typeLabel = new Label("Type:");
         priceLabel = new Label("Price:");
         damageLabel = new Label("Damage:");


        // Create VBox to stack labels and purchase button vertically
        VBox vbox = new VBox(10); // Set spacing between elements
        vbox.getChildren().addAll(nameLabel, typeLabel, priceLabel, damageLabel);
        vbox.setAlignment(Pos.CENTER_LEFT);
        AnchorPane.setRightAnchor(nameLabel,10.0);// Align elements in the center

        // Add VBox to the square
        square.add(vbox, 0, 0);

        // Set square size to fit the screen
        square.setMinSize(250, 200);
        square.setMaxSize(250, 200);

        return square;
    }



    private void fillLabels(GridPane square, int index) {
        VBox vbox = (VBox) square.getChildren().get(0); // Assuming the VBox is the first child of the square
        Label nameLabel = (Label) vbox.getChildren().get(0);
        Label typeLabel = (Label) vbox.getChildren().get(1);


            WeaponRegistry weaponRegistry = battle.getWeaponFactory().getWeaponShop().get(index + 1);

            // Set weapon name and type from arrays
            nameLabel.setText("Name : " + weaponNames[index]);
            typeLabel.setText("Type : " + weaponRegistry.getName());
            priceLabel.setText("Price : " + weaponRegistry.getPrice());
            damageLabel.setText("Damage : " + weaponRegistry.getDamage());

    }


    private void openLaneLevelScene(int purchaseWeaponCode) {
        laneLevelStage = new Stage();
        laneLevelStage.setTitle("Lane Selection");
        setIcon(laneLevelStage);
        VBox laneLevelRoot = new VBox(10);
        laneLevelRoot.setPadding(new Insets(10));

        // Get the number of lanes based on the difficulty
        int numLanes = getNumLanes();

        ArrayList<Button> buttonLanes = new ArrayList<>();

        // Create buttons for each lane
        for (int i = 0; i < numLanes; i++) {
            int laneNumber = i + 1;
            Button laneButton = new Button("Lane " + laneNumber);
            buttonLanes.add(laneButton);

            // Set action event for each lane button
            laneButton.setOnAction(actionEvent -> {
                handleLaneSelection(laneNumber, purchaseWeaponCode);
            });

            laneLevelRoot.getChildren().add(laneButton);
        }

        laneLevelRoot.setAlignment(Pos.CENTER);

        Scene laneLevelScene = new Scene(laneLevelRoot, 250, 300);
        laneLevelStage.setScene(laneLevelScene);
        laneLevelStage.setTitle("Lane Level Selection");
        laneLevelStage.show();
    }

    private int getNumLanes() {
        // Define the number of lanes for each difficulty level
        int numLanes;
        if (Difficulty.equals(Diffculty.EASY)){
            numLanes = 3;
        }
        else if (Difficulty.equals(Difficulty.HARD)){
            numLanes = 5;

        } else if (Difficulty.equals(Diffculty.VERYHARD)) {
            numLanes = 7;
        }
        else {
            numLanes = 0;
        }
        return numLanes;
    }

    private void handleLaneSelection(int laneNumber, int purchaseWeaponCode) {
        Lane lane = (Lane) battle.getOriginalLanes().toArray()[laneNumber-1];
        try {
                battle.purchaseWeapon(purchaseWeaponCode,lane);
                Update();
                WeaponStage.close();
        } catch (InsufficientResourcesException e) {
            showError(e.getMessage());

        } catch (InvalidLaneException e) {
            showError(e.getMessage());
        }

        finally {
            laneLevelStage.close();
        }
    }

    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void showPopUp(String title,String message) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // Block other windows until this one is closed
        popupStage.setTitle(title);

        Label messageLabel = new Label(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> popupStage.close());

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, closeButton);

        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);
        popupStage.showAndWait(); // Show the popup and wait until it is closed
    }

    private void saveHighscore(int highscore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.write(String.valueOf(highscore));
        } catch (IOException e) {
           showError(e.getMessage());
        }
    }
    private int loadHighscore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            showError(e.getMessage());
            return 0;
        } catch (IOException | NumberFormatException e) {
            showError(e.getMessage());
            return 0;
        }
    }

    public void EasterEgg(){
        Stage EasterEgg = new Stage();
        EasterEgg.setTitle("Easter Egg");
        setIcon(EasterEgg);
        Label titleLabel = new Label("\"You either die a hero or live long enough to see yourself become the villain.\"");
        Label bodyLabel = new Label("Hidden in the Survey Corps headquarters, a forgotten journal reveals a hero's fall into darkness. In \"Attack on Titan Utopia,\" your choices shape your fate. Will you stay a hero, or become the villain? The fate of humanity is in your hands.");
        VBox v = new VBox(10);
        v.getChildren().addAll(titleLabel, bodyLabel);
        v.setAlignment(Pos.CENTER);
        Scene easterEggScene = new Scene(v,400,300);
        EasterEgg.setScene(easterEggScene);
        EasterEgg.show();
    }

    private void closeAll(){
        try {
            BattleStage.close();
        }
        catch (Exception e) {

        }
        try {
            GameOver.close();
        }
        catch (Exception e) {

        }
        try{
            WeaponStage.close();
        }
        catch (Exception e) {

        }
        try {
            primaryStage.close();
        }
        catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void handle(ActionEvent actionEvent) {
    }

}

