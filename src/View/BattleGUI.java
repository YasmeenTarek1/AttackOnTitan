package View;

import Controller.Controller;

import Model.Exceptions.InsufficientResourcesException;
import Model.Engine.Battle;
import Model.Exceptions.InvalidLaneException;
import Model.Lanes.Lane;
import Model.Titans.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BattleGUI extends Scene {
    private static boolean FinishWeaponShop;
    private final StackPane root = new StackPane();
    private ArrayList<LanePane> lanesGUI;
    public static Battle b;
    private final int numOfLanes = ModeScene.numOfLanes;
    private final String mode = ModeScene.mode;
    private int choosenLaneToDeployWeapon;
    private Label score;
    private Label resourcesValue;
    private Label Turn;
    private Label battlePhase;
    private HashMap<Titan, TitanPane> mappingTitans;
    private final HashMap<Lane, LanePane> mappingLanes;
    private ArrayList<WeaponPane> Weapons;
    private Stage chooselanesstage;
    private Stage stage;
    private Timeline gameLoop;
    private ButtonGame pass , weaponShopIcon , AI;
    private int spacing;
    private HBox info , buttons;
    private StackPane box3;
    private Pane weaponShopRoot;

    public BattleGUI(StackPane p) {
        super(p, 1550, 870);

        playMedia("Media/Sounds/Battle Track.mp3");

        FinishWeaponShop = false;
        Weapons = new ArrayList<>();
        mappingTitans = new HashMap<>();
        mappingLanes = new HashMap<>();
        setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        try {
            if (mode.equals("easy"))
                b = new Battle(1, 0, 40, 3, 250);
            else {
                b = new Battle(1, 0, 45, 5, 125);
            }
        } catch (IOException e) {
            System.out.println("UnExpected Error");
        }

        setBackground(new Image("file:Media/Visuals/Battle background.jpg"));
        createLanes();
        IntializeInfo();

        setRoot(root);
        Controller.battleGUI = this;

        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            if (!b.isGameOver()) {
                weaponShopIcon.getButton().setOnAction(e -> {
                    chooseLanesStage();
                });
                pass.getButton().setOnAction(e -> {
                    b.passTurn();
                    updateGUI();
                });
                AI.getButton().setOnAction(e -> {
                    AI();
                    updateGUI();
                });
            } else {
                updateGUI();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted!");
                }

                gameLoop.stop();

                for(WeaponPane w : Weapons)
                    w.setVisible(false);

                BoxBlur blur = new BoxBlur(12, 12, 50);
                DropShadow glow = new DropShadow();        // glowing border
                glow.setColor(Color.LIGHTBLUE);
                glow.setWidth(40);                         // width of the glow
                glow.setHeight(40);                        // height of the glow
                glow.setSpread(0.5);

                for(Node node : root.getChildren()) {
                    if (node != info)
                        node.setEffect(blur);
                    else{
                        for(Node n : ((HBox)node).getChildren()){
                            if(n != box3)
                                n.setEffect(blur);
                            else
                                n.setEffect(glow);
                        }
                    }
                }

                ImageView img = new ImageView(new Image("file:Media/Visuals/Game Over.png"));
                img.setTranslateY(-100);
                img.setFitWidth(800);
                img.setFitHeight(250);

                ButtonGame NewGame = new ButtonGame(new Image("file:Media/Visuals/New Game Button.png"), 410, 160);
                ButtonGame Exit = new ButtonGame(new Image("file:Media/Visuals/Exit Button.png"), 400, 150);

                HBox h = new HBox(-20);
                h.getChildren().addAll(NewGame, Exit);
                h.setTranslateY(550);
                h.setTranslateX(352);

                root.getChildren().addAll(img , h);

                playMedia("Media/Sounds/Game Over.mp3");

                NewGame.getButton().setOnAction(e -> {
                    playMedia("Media/Sounds/Open Track.mp3");
                    Scene nxt = new StartMenuScene(new StackPane());
                    Controller.window.setScene(nxt);
                });

                Exit.getButton().setOnAction(e -> {
                    Controller.window.close();
                });
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        CharacterAppearanceEarly();
    }

    public void IntializeInfo() {
        AI = new ButtonGame(new Image("file:Media/Visuals/AI Button.png"), 110, 85);
        weaponShopIcon = new ButtonGame(new Image("file:Media/Visuals/Shop Button.png"), 400, 120);
        pass = new ButtonGame(new Image("file:Media/Visuals/Pass Button.png"), 400, 120);

        buttons = new HBox(-145, weaponShopIcon, pass, AI);
        AI.setTranslateY(30);
        AI.setTranslateX(76);

        buttons.setTranslateX(-80);
        buttons.setTranslateY(-32);

        ImageView img1 = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));
        ImageView img2 = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));
        ImageView img3 = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));
        ImageView img4 = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));

        img1.setFitWidth(210);
        img1.setFitHeight(90);
        img2.setFitWidth(210);
        img2.setFitHeight(90);
        img3.setFitWidth(210);
        img3.setFitHeight(90);
        img4.setFitWidth(210);
        img4.setFitHeight(90);

        Turn = new Label("Turn : " + b.getNumberOfTurns());
        resourcesValue = new Label("Money : " + b.getResourcesGathered());
        score = new Label("Score : " + b.getScore());
        battlePhase = new Label("" + b.getBattlePhase());

        Turn.setFont(new Font(20));
        resourcesValue.setFont(new Font(20));
        score.setFont(new Font(20));
        battlePhase.setFont(new Font(20));

        Turn.setTextFill(Color.WHITE);
        resourcesValue.setTextFill(Color.WHITE);
        score.setTextFill(Color.WHITE);
        battlePhase.setTextFill(Color.WHITE);

        StackPane box1 = new StackPane(img1, Turn);
        StackPane box2 = new StackPane(img2, resourcesValue);
        box3 = new StackPane(img3, score);
        StackPane box4 = new StackPane(img4, battlePhase);

        info = new HBox(-50, box1, box2, box3, box4);
        info.setAlignment(Pos.TOP_RIGHT);
        info.setTranslateX(20);
        info.setTranslateY(-390);

        root.getChildren().addAll(info, buttons);
    }

    private void chooseLanesStage() {
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Controller.window.close();
                System.exit(0);
            }
        });

        ImageView backgroundImageView = new ImageView(new Image("file:Media/Visuals/Weapon Shop Full.png"));
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);

        Pane chooselanesroot = new Pane(backgroundImageView);
        Scene scene = new Scene(chooselanesroot, 1550, 870);
        chooselanesstage = new Stage();

        HBox v = new HBox();

        ButtonGame lane1;
        ButtonGame lane2;
        ButtonGame lane3;
        ButtonGame lane4 = null;
        ButtonGame lane5 = null;

        if (numOfLanes == 3) {
            lane1 = new ButtonGame(new Image("file:Media/Visuals/lane_1.png"), 700, 420);
            lane2 = new ButtonGame(new Image("file:Media/Visuals/lane_2.png"), 700, 420);
            lane3 = new ButtonGame(new Image("file:Media/Visuals/lane_3.png"), 700, 420);
            v.setSpacing(-200);
            v.setTranslateY(260);
            v.setTranslateX(-128);
            v.getChildren().addAll(lane1, lane2, lane3);
        } else {
            lane1 = new ButtonGame(new Image("file:Media/Visuals/lane_1.png"), 600, 340);
            lane2 = new ButtonGame(new Image("file:Media/Visuals/lane_2.png"), 600, 340);
            lane3 = new ButtonGame(new Image("file:Media/Visuals/lane_3.png"), 600, 340);
            lane4 = new ButtonGame(new Image("file:Media/Visuals/lane_4.png"), 600, 340);
            lane5 = new ButtonGame(new Image("file:Media/Visuals/lane_5.png"), 600, 340);
            v.setSpacing(-320);
            v.setTranslateY(305);
            v.setTranslateX(-138);
            v.getChildren().addAll(lane1, lane2, lane3, lane4, lane5);
        }
        chooselanesroot.getChildren().addAll(v);
        chooselanesroot.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        chooselanesstage.setScene(scene);
        chooselanesstage.initOwner(Controller.window);
        chooselanesstage.show();

        lane1.getButton().setOnAction(e -> {
            choosenLaneToDeployWeapon = numOfLanes - 1;
            chooselanesstage.close();
            weaponshopstage();
        });
        lane2.getButton().setOnAction(e -> {
            choosenLaneToDeployWeapon = numOfLanes - 2;
            chooselanesstage.close();
            weaponshopstage();
        });
        lane3.getButton().setOnAction(e -> {
            choosenLaneToDeployWeapon = numOfLanes - 3;
            chooselanesstage.close();
            weaponshopstage();
        });
        if (numOfLanes == 5) {
            lane4.getButton().setOnAction(e -> {
                choosenLaneToDeployWeapon = numOfLanes - 4;
                chooselanesstage.close();
                weaponshopstage();
            });
            lane5.getButton().setOnAction(e -> {
                choosenLaneToDeployWeapon = numOfLanes - 5;
                chooselanesstage.close();
                weaponshopstage();
            });
        }
    }

    private void CharacterAppearanceEarly() {

        for (Node node : root.getChildren())
            node.setEffect(new GaussianBlur(15));

        ImageView imageView;
        String path;
        ImageView characterIntro;
        ImageView generalInfo = new ImageView(new Image("file:Media/Visuals/At the start of each turn.png"));
        switch ((CharactersScene.character)) {
            case "Levi" : {
                imageView = new ImageView(new Image("file:Media/Visuals/Levi No-Background.png"));
                path = "Media/Sounds/Hey I'm Levi.mp3";
                characterIntro = new ImageView(new Image("file:Media/Visuals/Levi Text.png"));
                break;
            }
            case "Mikasa" : {
                imageView = new ImageView("file:Media/Visuals/Mikasa No-Background.png");
                path = "Media/Sounds/Hey I'm Mikasa.mp3";
                characterIntro = new ImageView(new Image("file:Media/Visuals/Mikasa Text.png"));
                break;
            }
            case "Armin" : {
                imageView = new ImageView(new Image("file:Media/Visuals/Armin No-Background.png"));
                path = "Media/Sounds/Hey I'm Armin.mp3";
                characterIntro = new ImageView(new Image("file:Media/Visuals/Armin Text.png"));
                break;
            }
            default : {
                imageView = new ImageView(new Image("file:Media/Visuals/Eren No-Background.png"));
                path = "Media/Sounds/Hey I'm Eren.mp3";
                characterIntro = new ImageView(new Image("file:Media/Visuals/Eren Text.png"));
            }
        }
        Pane image = new Pane(imageView);
        imageView.setLayoutX(900);
        imageView.setLayoutY(375);

        imageView.setFitHeight(700);
        imageView.setFitWidth(530);

        playMedia(path);
        ButtonGame next = new ButtonGame(new Image("file:Media/Visuals/Next Button.png"), 300, 100);
        Pane buttonPane = new Pane(next);

        root.getChildren().addAll(buttonPane, image, characterIntro);
        buttonPane.toFront();
        next.setDisable(false);

        next.getButton().setOnAction(
        e -> {

            DropShadow glow = new DropShadow();        // glowing border
            glow.setColor(Color.LIGHTBLUE);
            glow.setWidth(30);                         // width of the glow
            glow.setHeight(30);                        // height of the glow
            glow.setSpread(0.5);

            buttons.setEffect(new GaussianBlur(0));
            for(Node n : buttons.getChildren()) {
                if (n == AI)
                    n.setEffect(new GaussianBlur(15));
                else {
                    n.setEffect(new GaussianBlur(0));
                    n.setEffect(glow);
                }
            }

            if ((CharactersScene.character).equals("Mikasa"))
                playMedia("Media/Sounds/At the start of each turn Women.mp3");
            else
                playMedia("Media/Sounds/At the start of each turn Man.mp3");
            characterIntro.setVisible(false);
            root.getChildren().remove(characterIntro);
            root.getChildren().add(generalInfo);

            ButtonGame next2 = new ButtonGame(new Image("file:Media/Visuals/Next Button.png"), 300, 100);
            next2.setTranslateX(600);
            next2.setTranslateY(620);
            next.setVisible(false);
            next.getButton().setDisable(true);
            buttonPane.getChildren().addAll(next2);

            next2.getButton().setOnAction(
                b -> {
                    playMedia("Media/Sounds/Battle Track.mp3");

                    generalInfo.setVisible(false);
                    image.setVisible(false);
                    next2.setVisible(false);

                    root.getChildren().removeAll(buttonPane, image,
                            generalInfo);

                    for (Node node : root.getChildren())
                        node.setEffect(new GaussianBlur(0));
                    for(Node n : buttons.getChildren())
                        n.setEffect(new GaussianBlur(0));
                });
        });
        next.setTranslateX(600);
        next.setTranslateY(620);

    }
    private void characterAppearPhase() {

        if (b.getNumberOfTurns() != 15 && b.getNumberOfTurns() != 30)
            return;

        for (Node node : root.getChildren())
            node.setEffect(new GaussianBlur(15));

        if(b.getNumberOfTurns() == 15) {
            DropShadow glow = new DropShadow();        // glowing border
            glow.setColor(Color.LIGHTBLUE);
            glow.setWidth(30);                         // width of the glow
            glow.setHeight(30);                        // height of the glow
            glow.setSpread(0.5);

            buttons.setEffect(new GaussianBlur(0));
            for (Node n : buttons.getChildren()) {
                if (n != AI)
                    n.setEffect(new GaussianBlur(15));
                else {
                    n.setEffect(new GaussianBlur(0));
                    n.setEffect(glow);
                }
            }
        }

        ImageView imageView;
        String path;
        ImageView text;

        if (b.getNumberOfTurns() == 30) {
            if (CharactersScene.character.equals("Mikasa"))
                path = "Media/Sounds/Grumbling Woman.mp3";
            else
                path = "Media/Sounds/Grumbling Man.mp3";
            text = new ImageView(new Image("file:Media/Visuals/Grumbling Text.png"));
        }
        else {
            if (CharactersScene.character.equals("Mikasa"))
                path = "Media/Sounds/Intense Woman.mp3";
            else
                path = "Media/Sounds/Intense Man.mp3";
            text = new ImageView(new Image("file:Media/Visuals/Intense Text.png"));
        }

        imageView = switch ((CharactersScene.character)) {
            case "Levi" -> new ImageView(new Image("file:Media/Visuals/Levi No-Background.png"));
            case "Mikasa" -> new ImageView("file:Media/Visuals/Mikasa No-Background.png");
            case "Armin" -> new ImageView(new Image("file:Media/Visuals/Armin No-Background.png"));
            default -> new ImageView(new Image("file:Media/Visuals/Eren No-Background.png"));
        };
        Pane image = new Pane(imageView);
        imageView.setLayoutX(900);
        imageView.setLayoutY(375);
        imageView.setFitHeight(700);
        imageView.setFitWidth(530);

        playMedia(path);
        ButtonGame next = new ButtonGame(new Image("file:Media/Visuals/Next Button.png"), 300, 100);

        Pane buttonPane = new Pane(next);

        root.getChildren().addAll(buttonPane, image, text);
        buttonPane.toFront();

        next.getButton().setOnAction(b -> {
            playMedia("Media/Sounds/Battle Track.mp3");
            text.setVisible(false);
            image.setVisible(false);
            next.setVisible(false);
            root.getChildren().removeAll(buttonPane, image, text);

            for (Node node : root.getChildren())
                node.setEffect(new GaussianBlur(0));
            for(Node n : buttons.getChildren())
                n.setEffect(new GaussianBlur(0));
        });

        next.setTranslateX(600);
        next.setTranslateY(620);

    }

    private void playMedia(String path) {
        Media media = new Media(new File(path).toURI().toString());
        Controller.music.stop();
        Controller.music = new MediaPlayer(media);
        Controller.music.play();
    }

    public void weaponshopstage() {
        FinishWeaponShop = false;
        ImageView backgroundImageView = new ImageView(new Image("file:Media/Visuals/Weapon Shop Empty.png"));
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);

        weaponShopRoot = new Pane();
        weaponShopRoot.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        ImageView moneyy = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));
        moneyy.setFitWidth(350);
        moneyy.setFitHeight(200);

        Label label = new Label(BattleGUI.b.getResourcesGathered() + "");
        label.setPrefSize(140, 100);

        label.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font(55));
        label.setTranslateX(18);

        StackPane money = new StackPane(moneyy, label);
        money.setTranslateX(1150);
        money.setTranslateY(70);

        weaponShopRoot.getChildren().addAll(backgroundImageView, money);

        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        Image weapon1 = new Image("file:Media/Visuals/Piercing Cannon.png");
        Image weapon2 = new Image("file:Media/Visuals/Volley Spread Cannon.png");
        Image weapon3 = new Image("file:Media/Visuals/Sniper Cannon.png");
        Image weapon4 = new Image("file:Media/Visuals/Wall Trap.png");

        ButtonGame w1 = new ButtonGame(weapon1, 360, 170);// piercing
        ButtonGame w2 = new ButtonGame(weapon2, 300, 100);// volley
        ButtonGame w3 = new ButtonGame(weapon3, 350, 230); // sniper
        ButtonGame w4 = new ButtonGame(weapon4, 400, 200);// walltrap


        w1.setLayoutX(240);
        w1.setLayoutY(185);

        w2.setLayoutX(600);
        w2.setLayoutY(210);

        w3.setLayoutX(750);
        w3.setLayoutY(600);

        w4.setLayoutX(100);
        w4.setLayoutY(630);

        ImageView PiercingInfo = new ImageView(new Image("file:Media/Visuals/Piercing Cannon Info.png"));
        ImageView VolleyInfo = new ImageView(new Image("file:Media/Visuals/Sniper Cannon Info.png"));
        ImageView SniperInfo = new ImageView(new Image("file:Media/Visuals/Volley Spread Cannon Info.png"));
        ImageView WallTrapInfo = new ImageView(new Image("file:Media/Visuals/Wall Trap Info.png"));

        PiercingInfo.setFitWidth(800);
        SniperInfo.setFitWidth(800);
        VolleyInfo.setFitWidth(800);
        WallTrapInfo.setFitWidth(800);

        PiercingInfo.setVisible(false);
        SniperInfo.setVisible(false);
        VolleyInfo.setVisible(false);
        WallTrapInfo.setVisible(false);

        Pane v1 = new Pane();
        v1.getChildren().addAll(w1, w2, w3, w4);

        Pane info = new Pane();
        info.getChildren().addAll(PiercingInfo, SniperInfo, VolleyInfo,
                WallTrapInfo);

        weaponShopRoot.getChildren().addAll(info, v1);

        stage = new Stage();
        stage.setScene(new Scene(weaponShopRoot, 1550, 870));
        stage.initOwner(Controller.window);
        stage.show();

        w1.setOnMouseEntered(event -> {
            PiercingInfo.setVisible(true);
        });
        w1.setOnMouseExited(event -> {
            PiercingInfo.setVisible(false);
        });
        w1.getButton().setOnAction(e -> {
            int weaponCode = 1;
            buy(weaponCode, choosenLaneToDeployWeapon);
            if (FinishWeaponShop)
                stage.close();
        });
        w2.setOnMouseEntered(event -> {
            SniperInfo.setVisible(true);
        });
        w2.setOnMouseExited(event -> {
            SniperInfo.setVisible(false);
        });
        w2.getButton().setOnAction(e -> {
            int weaponCode = 3;
            buy(weaponCode, choosenLaneToDeployWeapon);
            if (FinishWeaponShop)
                stage.close();
        });
        w3.setOnMouseEntered(event -> {
            VolleyInfo.setVisible(true);
        });
        w3.setOnMouseExited(event -> {
            VolleyInfo.setVisible(false);
        });
        w3.getButton().setOnAction(e -> {
            int weaponCode = 2;
            buy(weaponCode, choosenLaneToDeployWeapon);
            if (FinishWeaponShop)
                stage.close();
        });
        w4.setOnMouseEntered(event -> {
            WallTrapInfo.setVisible(true);
        });
        w4.setOnMouseExited(event -> {
            WallTrapInfo.setVisible(false);
        });
        w4.getButton().setOnAction(e -> {
            int weaponCode = 4;
            buy(weaponCode, choosenLaneToDeployWeapon);
            if (FinishWeaponShop)
                stage.close();
        });
    }

    private void buy(int code, int choosenLaneToDeployWeapon) {
        try {
            b.purchaseWeapon(code, b.getOriginalLanes().get(choosenLaneToDeployWeapon));
            deployWeapon(code, choosenLaneToDeployWeapon);
            Controller.window.setScene(Controller.battleGUI);
            updateGUI();
            FinishWeaponShop = true;
        } catch (InvalidLaneException e) {

            ImageView invalidlanesMessage = new ImageView(new Image("file:Media/Visuals/Defeated Lanes Exception.png"));
            invalidlanesMessage.setFitWidth(300);
            invalidlanesMessage.setFitHeight(340);
            invalidlanesMessage.setTranslateX(15);

            ButtonGame lanes = new ButtonGame(new Image("file:Media/Visuals/Lanes Button.png"), 475, 145);
            lanes.setTranslateX(-85);
            lanes.setTranslateY(-5);

            VBox InvalidLane = new VBox();
            InvalidLane.getChildren().add(lanes);

            VBox all = new VBox();
            all.getChildren().addAll(invalidlanesMessage, InvalidLane);
            all.setLayoutX(600);
            all.setLayoutY(160);
            all.toFront();

            weaponShopRoot.getChildren().add(all);

            for (Node node : weaponShopRoot.getChildren())
                if (node != all)
                    node.setEffect(new GaussianBlur(17));

            lanes.getButton().setOnAction(a -> {
                stage.close();
                chooselanesstage.close();
                chooseLanesStage();
                for (Node node : weaponShopRoot.getChildren())
                    if (node != all)
                        node.setEffect(null);
            });
        } catch (InsufficientResourcesException e) {

            ImageView insufficientMessage = new ImageView(new Image("file:Media/Visuals/No Enough Resources Exception.png"));
            insufficientMessage.setFitWidth(300);
            insufficientMessage.setFitHeight(340);
            insufficientMessage.setTranslateX(15);

            ButtonGame anotherWeapon = new ButtonGame(new Image("file:Media/Visuals/Purchase Another Weapon Button.png"), 300, 100);
            ButtonGame ExitShop = new ButtonGame(new Image("file:Media/Visuals/Exit Shop Button.png"), 300, 100);

            VBox Insufficient = new VBox();
            Insufficient.getChildren().addAll(anotherWeapon, ExitShop);

            VBox all = new VBox();
            all.getChildren().addAll(insufficientMessage, Insufficient);
            all.setLayoutX(600);
            all.setLayoutY(100);
            all.toFront();

            weaponShopRoot.getChildren().add(all);

            for (Node node : weaponShopRoot.getChildren())
                if (node != all)
                    node.setEffect(new GaussianBlur(17));

            anotherWeapon.getButton().setOnAction(q -> {
                stage.close();
                weaponshopstage();
                for (Node node : weaponShopRoot.getChildren())
                    if (node != all)
                        node.setEffect(null);
            });
            ExitShop.getButton().setOnAction(q -> {
                stage.close();
                Controller.window.setScene(Controller.battleGUI);
                for (Node node : weaponShopRoot.getChildren())
                    if (node != all)
                        node.setEffect(null);
            });
        }
        resourcesValue.setText("" + b.getResourcesGathered());
    }

    public void deployWeapon(int code, int lane) {
        WeaponPane weaponPane = new WeaponPane(50, 50, code, -90, 110);
        Weapons.add(weaponPane);
        LanePane lanepane = lanesGUI.get(lane);
        lanepane.wall.addweapon(weaponPane);
    }

    public int[] tryOption(Battle test, Lane lane, int WeaponCode) {
        int[] arr = new int[2];
        try {
            test.purchaseWeapon(WeaponCode, lane);
        } catch (Exception e) {
            return new int[]{-1 , -1};
        }

        arr[0] = test.getScore();
        arr[1] = test.getResourcesGathered();
        return arr;
    }

    private void AI() {
        int maxScore = -1, maxMoney = -1, WeaponCode = -1, lane = -1;
        for (int i = 0; i < numOfLanes; i++) {     // loop on lanes

            for (int j = 1; j <= 4; j++) {         // loop on weapons

                Battle test = b.clone();           // create new battle with the same state of b
                ArrayList<Lane> temp = new ArrayList<>();

                for (int k = 0; k < i; k++)       // get the chosen Lane
                    temp.add(test.getLanes().poll());

                int o;
                for (o = 0; o < test.getOriginalLanes().size(); o++)
                    if (test.getOriginalLanes().get(o) == test.getLanes().peek())
                        break;

                Lane ChosenLane = test.getLanes().peek();

                for(Lane l : temp)
                    test.getLanes().add(l);

                int[] arr = tryOption(test, ChosenLane, j);
                if ((arr[0] == maxScore && arr[1] > maxMoney) || arr[0] > maxScore) {
                    maxScore = arr[0];
                    maxMoney = arr[1];
                    WeaponCode = j;
                    lane = o;
                }
            }
        }
        try {
            b.purchaseWeapon(WeaponCode, b.getOriginalLanes().get(lane));
            deployWeapon(WeaponCode, lane);
        } catch (Exception e) {
            b.passTurn();
        }
    }

    public void createLanes() {

        // Container for walls
        HBox wallContainer = new HBox();
        // Container for lanes
        VBox laneContainer = new VBox();

        if (numOfLanes == 3) {
            spacing = -95;
            lanesGUI = new ArrayList<>();

            LanePane l1 = new LanePane(550, 250, 110, -250, -60, 35, new Image("file:Media/Visuals/Easy Wall.png"), 245, 245, -150, -60, 705, 25);
            LanePane l2 = new LanePane(550, 250, -140, -160, -60, 35, new Image("file:Media/Visuals/Easy Wall.png"), 245, 245, 0, 65, 720, 20);
            LanePane l3 = new LanePane(550, 250, -435, -100, -60, 35, new Image("file:Media/Visuals/Easy Wall.png"), 245, 245, 125, 200, 770, 20);

            lanesGUI.add(l1);
            lanesGUI.add(l2);
            lanesGUI.add(l3);

            laneContainer.getChildren().addAll(l1, l2, l3);
            laneContainer.setAlignment(Pos.CENTER);
            laneContainer.setTranslateY(-180);
            laneContainer.setSpacing(-200);

            wallContainer.getChildren().addAll(l3.wall, l2.wall, l1.wall);
            wallContainer.setAlignment(Pos.BOTTOM_RIGHT);
            wallContainer.setTranslateY(-300);
            wallContainer.setSpacing(125);

            root.getChildren().addAll(laneContainer, wallContainer);
            mappingLanes.put(b.getOriginalLanes().get(0), l1);
            mappingLanes.put(b.getOriginalLanes().get(1), l2);
            mappingLanes.put(b.getOriginalLanes().get(2), l3);

        } else {
            spacing = -70;
            lanesGUI = new ArrayList<>();
            LanePane l1 = new LanePane(500, 150, 230, -305, -50, 35, new Image("file:Media/Visuals/Hard Wall.png"), 200, 200, -300, -280, 700, -20);
            LanePane l2 = new LanePane(550, 150, 145, -260, -50, 35, new Image("file:Media/Visuals/Hard Wall.png"), 200, 200, -240, -190, 700, 20);
            LanePane l3 = new LanePane(550, 150, 20, -215, -50, 35, new Image("file:Media/Visuals/Hard Wall.png"), 200, 200, -190, -105, 700, 45);
            LanePane l4 = new LanePane(550, 150, -160, -135, -50, 35, new Image("file:Media/Visuals/Hard Wall.png"), 200, 200, -140, -34, 720, 32);
            LanePane l5 = new LanePane(550, 150, -320, -80, -50, 35, new Image("file:Media/Visuals/Hard Wall.png"), 200, 200, -90, 45, 720, 40);

            lanesGUI.add(l1);
            lanesGUI.add(l2);
            lanesGUI.add(l3);
            lanesGUI.add(l4);
            lanesGUI.add(l5);

            laneContainer.getChildren().addAll(l1, l2, l3, l4, l5);
            laneContainer.setAlignment(Pos.CENTER);
            laneContainer.setSpacing(-140);
            laneContainer.setTranslateY(-185);
            laneContainer.setTranslateX(-150);

            wallContainer.getChildren().addAll(l5.wall, l4.wall, l3.wall, l2.wall, l1.wall);
            wallContainer.setAlignment(Pos.CENTER_RIGHT);
            wallContainer.setTranslateX(205);
            wallContainer.setTranslateY(200);

            root.getChildren().addAll(laneContainer, wallContainer);

            mappingLanes.put(b.getOriginalLanes().get(0), l1);
            mappingLanes.put(b.getOriginalLanes().get(1), l2);
            mappingLanes.put(b.getOriginalLanes().get(2), l3);
            mappingLanes.put(b.getOriginalLanes().get(3), l4);
            mappingLanes.put(b.getOriginalLanes().get(4), l5);
        }
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
        backgroundImageView.toBack();
    }

    private TitanPane convertTitanToTitanPane(Titan t) {
        int w, h;
        TitanPane tt = null;
        if (t instanceof PureTitan) {
            if (mode.equals("easy")) {
                w = 100;
                h = 100;
            } else {
                w = 85;
                h = 85;
            }
            tt = new TitanPane(new Image("file:Media/Visuals/Pure Titan.png"), w, h);
        } else if (t instanceof AbnormalTitan) {
            if (mode.equals("easy")) {
                w = 80;
                h = 80;
            } else {
                w = 75;
                h = 75;
            }
            tt = new TitanPane(new Image("file:Media/Visuals/Abnormal Titan.png"), w, h);
        } else if (t instanceof ColossalTitan) {
            if (mode.equals("easy")) {
                w = 120;
                h = 120;
            } else {
                w = 95;
                h = 95;
            }
            tt = new TitanPane(new Image("file:Media/Visuals/Colossal Titan.png"), w, h);
        } else if (t instanceof ArmoredTitan) {
            if (mode.equals("easy")) {
                w = 110;
                h = 110;
            } else {
                w = 85;
                h = 85;
            }
            tt = new TitanPane(new Image("file:Media/Visuals/Armored Titan.png"), w, h);
        }
        return tt;
    }

    public void updateGUI() {
        characterAppearPhase();
        addTurnTitansToLaneGUI();
        moveTitansGUI();
        performWeaponsAttacksGUI();
        performTitansAttacksGUI();
        updateLanesDangerLevelsGUI();
        finalizeTurnsGUI();
    }

    private void addTurnTitansToLaneGUI() {
        if(b.getAddedLane() == null || !mappingLanes.containsKey(b.getAddedLane()) || b.isGameOver())
            return;
        LanePane p = mappingLanes.get(b.getAddedLane());
        VBox v = new VBox(spacing);
        for (Titan t : b.getAddedTitansToTheLane()) {
            TitanPane tt = convertTitanToTitanPane(t);
            v.getChildren().add(tt);
            tt.hpBar.setProgress(1);
            mappingTitans.put(t, tt);
        }
        p.addTitan(v);
    }

    public void moveTitansGUI() {
        for (Titan t : mappingTitans.keySet()) {
            if (t.getDistance() > 0 && t.getCurrentHealth() > 0) {
                TitanPane tt = mappingTitans.get(t);
                tt.moveTitan(t.getSpeed() / 5);
            }
        }
    }

    private void performWeaponsAttacksGUI() {

        HashMap<Titan, TitanPane> mappingTitans2 = new HashMap<>(); // compare with the curTitans to know the difference

        for (Lane l : b.getLanes())
            for (Titan t : l.getTitans())
                mappingTitans2.put(t, mappingTitans.get(t)); // all existing Titans after this turn in GUI

        for (Titan t : mappingTitans.keySet()) {
            double health = t.getCurrentHealth() * 1.0 / t.getBaseHealth();
            mappingTitans.get(t).hpBar.setProgress(health);
            mappingTitans.get(t).hpBar.setStyle("-fx-accent: green;");
            if (health < 0.5)
                mappingTitans.get(t).hpBar.setStyle("-fx-accent: red;");

            // Handling dead Titans

            if (!mappingTitans2.containsKey(t)) { // we should differentiate between dead and rest after lost lane
                TitanPane tt = mappingTitans.get(t);
                tt.hpBar.setVisible(false);

                if(tt.hpBar.getProgress() < 0.15) {
                    tt.setEffect();
                    tt.hpBar.setProgress(0);
                }

                FadeTransition fade1 = new FadeTransition(Duration.seconds(1.1), tt);
                fade1.setFromValue(1.0);
                fade1.setToValue(0);
                fade1.play();

                fade1.setOnFinished(e -> {
                    tt.setVisible(false);
                    tt.getChildren().remove(tt.Explosion);
                });
            }
        }
        mappingTitans = new HashMap<>(mappingTitans2);

        resourcesValue.setText("Money : " + b.getResourcesGathered());
        score.setText("Score : " + b.getScore());
    }

    private void performTitansAttacksGUI() {
        for (Lane l : b.getLanes()) {
            double newHealth = l.getLaneWall().getCurrentHealth() * 1.0 / l.getLaneWall().getBaseHealth();
            mappingLanes.get(l).wall.hpBar.setProgress(newHealth);
            if (newHealth < 0.5) {
                mappingLanes.get(l).wall.hpBar.setStyle("-fx-accent: red;");
                if(!mappingLanes.get(l).wall.fire) {
                    mappingLanes.get(l).wall.setEffectMID();
                    mappingLanes.get(l).wall.fire = true;
                }
            }
        }

        // Handling Lost Lanes

        ArrayList<Lane> toBeRemoved = new ArrayList<>();
        for (Lane l : mappingLanes.keySet()) {
            if (!b.getLanes().contains(l)) { // Lost Lane
                mappingLanes.get(l).wall.hpBar.setVisible(false);

                mappingLanes.get(l).wall.setEffectEND();
                toBeRemoved.add(l);
            }
        }
        for (Lane l : toBeRemoved)
            mappingLanes.remove(l);
    }

    private void finalizeTurnsGUI() {
        Turn.setText("Turn : " + b.getNumberOfTurns());
        battlePhase.setText("" + b.getBattlePhase());
    }

    private void updateLanesDangerLevelsGUI() {
        for (Lane l : b.getLanes()) {
            mappingLanes.get(l).dangerLevel.setText("DL: " + l.getDangerLevel());
        }
    }

}
