package View;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class ButtonGame extends Pane {

    private Button button;
    private ImageView view;

    public ButtonGame(Image img, int w, int h) {
        view = new ImageView(img);

        view.setFitWidth(w);
        view.setFitHeight(h);


        button = new Button();
        button.setGraphic(view);                               // Create a Button with the StackPane as its graphic
        button.setStyle("-fx-background-color: transparent;");      // Make the button transparent


        DropShadow glow = new DropShadow();        // glowing border
        glow.setColor(Color.LIGHTBLUE);
        glow.setWidth(30);                         // width of the glow
        glow.setHeight(30);                        // height of the glow
        glow.setSpread(0.5);

        button.setOnMouseEntered(
                e -> {
                    animateButton(button, 7.5, -5);     // Move button up when hovered
                    button.setEffect(glow);
                }
        );

        button.setOnMouseExited(
                e -> {
                    animateButton(button, 0, 0);   // Move button down when unhovered
                    button.setEffect(null);
                }
        );
        button.setOnMousePressed(
                e -> {
                    Media song = new Media(new File("Media/Sounds/Button Click.mp3").toURI().toString());
                    MediaPlayer music = new MediaPlayer(song);
                    MediaView mediaView = new MediaView(music);
                    mediaView.getMediaPlayer().play();
                    animateButton(button, 0, 0);   // Move button down when unhovered
                }
        );

        getChildren().add(button);                         // Add the button to the pane
    }

    private void animateButton(Button button, double deltaY, double deltaX) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), button);
        transition.setToY(deltaY);
        transition.setToX(deltaX);
        transition.play();
    }

    public Button getButton() {
        return button;
    }

    public ImageView getView() {
        return view;
    }
}
