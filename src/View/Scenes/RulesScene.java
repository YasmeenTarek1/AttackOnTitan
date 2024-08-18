package View.Scenes;

import View.GameElements.ButtonGame;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class RulesScene extends Scene {

    public StackPane root = new StackPane();
    private final ButtonGame homeButton;
    private final ButtonGame soundButton;
    private final ButtonGame muteButton;

    public RulesScene(StackPane p) {
        super(p);
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        setBackground(new Image("file:Media/Visuals/Background 2.jpg"));

        ImageView scroll = new ImageView(new Image("file:Media/Visuals/Game Rules.png"));
        scroll.setFitHeight(850);
        scroll.setFitWidth(1150);
        scroll.setTranslateX(-20);
        scroll.setTranslateY(-10);

        homeButton = new ButtonGame(new Image("file:Media/Visuals/Home Button.png"), 90, 90);
        soundButton = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
        muteButton = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);

        soundButton.setTranslateX(1430);
        soundButton.setTranslateY(-11);

        root.getChildren().addAll(homeButton, scroll, soundButton);
        this.setRoot(root);
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }

    public ButtonGame getHomeButton() {
        return homeButton;
    }

    public ButtonGame getSoundButton() {
        return soundButton;
    }

    public ButtonGame getMuteButton() {
        return muteButton;
    }

}