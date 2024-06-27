package View;

import Controller.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StartMenuScene extends Scene {

    private StackPane root = new StackPane();
    private VBox menuBox = new VBox();
    private ButtonGame ButtonGame1 = new ButtonGame(new Image("file:Media/Visuals/Start Button.png"), 300, 100);
    private ButtonGame ButtonGame2 = new ButtonGame(new Image("file:Media/Visuals/Rules Button.png"), 300, 100);
    private ButtonGame ButtonGame3 = new ButtonGame(new Image("file:Media/Visuals/Credit Button.png"), 300, 100);
    private ButtonGame ButtonGame4 = new ButtonGame(new Image("file:Media/Visuals/Quit Button.png"), 300, 100);


    public StartMenuScene(StackPane p) {
        super(p, 1550 , 870);
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        root = createContent();

        ButtonGame1.getButton().setOnAction(
                e -> {
                    Scene nxt = new CharactersScene(new StackPane());
                    Controller.window.setScene(nxt);
                });

        ButtonGame2.getButton().setOnAction(
                e -> {
                    Scene nxt = new RulesScene(new StackPane());
                    Controller.window.setScene(nxt);
                });

        ButtonGame3.getButton().setOnAction(
                e -> {
                    Scene nxt = new CreditsScene(new StackPane());
                    Controller.window.setScene(nxt);
                });

        ButtonGame4.getButton().setOnAction(e -> Controller.window.close());

        setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ESCAPE) {
                    Controller.window.close();
                    System.exit(0);
                }
        });

        this.setRoot(root);

        ButtonGame sound = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
        ButtonGame mute = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);

        root.getChildren().add(sound);
        sound.setTranslateX(1430);
        sound.setTranslateY(-11);

        sound.getButton().setOnAction(e -> {   // there is sound, so mute it
            Controller.music.stop();
            root.getChildren().remove(sound);
            root.getChildren().add(mute);
            mute.setTranslateX(1430);
        });
        mute.getButton().setOnAction(e -> { // there is mute, so turn it on
            Controller.music.play();
            root.getChildren().remove(mute);
            root.getChildren().add(sound);
            sound.setTranslateX(1430);
        });


    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }

    private void addTitle() {
        Image img = new Image("file:Media/Visuals/Start Menu Logo.png");
        ImageView view = new ImageView(img);
        view.setFitWidth(600);
        view.setFitHeight(200);
        view.setTranslateX(10);
        view.setTranslateY(-280);
        view.setEffect(new DropShadow(25, Color.BLACK));
        root.getChildren().add(view);
    }

    private StackPane createContent() {
        Image img = new Image("file:Media/Visuals/Background 2.jpg");
        setBackground(img);
        addTitle();
        menuBox.getChildren().addAll(ButtonGame1, ButtonGame2, ButtonGame3, ButtonGame4);
        menuBox.setAlignment(Pos.CENTER);

        root.getChildren().add(menuBox);

        menuBox.setTranslateX(600);
        menuBox.setTranslateY(60);

        startAnimation();
        return root;
    }

    private void startAnimation() {
        SequentialTransition seqTransition = new SequentialTransition(); // combine multiple animations

        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Node button = menuBox.getChildren().get(i);

            button.setOpacity(0); // hidden

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), button); // gradually increase its opacity
            fadeTransition.setToValue(1); // visible
            if (i == 0)
                fadeTransition.setDelay(Duration.seconds(0.5));
            else
                fadeTransition.setDelay(Duration.seconds(0.07 * i)); // Delay each button's animation

            seqTransition.getChildren().add(fadeTransition);
        }

        seqTransition.play();
    }

}