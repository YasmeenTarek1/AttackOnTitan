package View.Scenes;

import View.GameElements.ButtonGame;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StartMenuScene extends Scene {

    public StackPane root = new StackPane();
    private final VBox menuBox = new VBox();
    private final ButtonGame ButtonGame1 = new ButtonGame(new Image("file:Media/Visuals/Start Button.png"), 300, 100);
    private final ButtonGame ButtonGame2 = new ButtonGame(new Image("file:Media/Visuals/Rules Button.png"), 300, 100);
    private final ButtonGame ButtonGame3 = new ButtonGame(new Image("file:Media/Visuals/Credit Button.png"), 300, 100);
    private final ButtonGame ButtonGame4 = new ButtonGame(new Image("file:Media/Visuals/Quit Button.png"), 300, 100);
    private final ButtonGame soundButton = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
    private final ButtonGame muteButton = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);


    public StartMenuScene(StackPane p) {
        super(p, 1550, 870);

        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        setBackground(new Image("file:Media/Visuals/Background 2.jpg"));
        addTitle();

        menuBox.getChildren().addAll(ButtonGame1, ButtonGame2, ButtonGame3, ButtonGame4);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(600);
        menuBox.setTranslateY(60);

        soundButton.setTranslateX(1430);
        soundButton.setTranslateY(-11);

        startAnimation();

        root.getChildren().addAll(menuBox, soundButton);
        this.setRoot(root);
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

    public VBox getMenuBox() {
        return menuBox;
    }

    public ButtonGame getButtonGame1() {
        return ButtonGame1;
    }

    public ButtonGame getButtonGame2() {
        return ButtonGame2;
    }

    public ButtonGame getButtonGame3() {
        return ButtonGame3;
    }

    public ButtonGame getButtonGame4() {
        return ButtonGame4;
    }

    public ButtonGame getSoundButton() {
        return soundButton;
    }

    public ButtonGame getMuteButton() {
        return muteButton;
    }
}