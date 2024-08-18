package View.Scenes;

import View.GameElements.ButtonGame;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class CreditsScene extends Scene {

    public Pane root = new Pane();
    private final ButtonGame homeButton;
    private final ButtonGame soundButton;
    private final ButtonGame muteButton;
    private final MediaPlayer mediaPlayer;

    public CreditsScene(Pane paneRoot) {
        super(paneRoot);
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        Media media = new Media(new File("Media/Sounds/Credits Video.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView Video = new MediaView(mediaPlayer);

        Video.setFitWidth(1550);
        Video.setFitHeight(870);
        Video.setPreserveRatio(true);

        mediaPlayer.play();

        homeButton = new ButtonGame(new Image("file:Media/Visuals/Home Button.png"), 90, 90);
        soundButton = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
        muteButton = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);

        soundButton.setTranslateX(1430);
        soundButton.setTranslateY(-11);

        root.getChildren().addAll(Video, homeButton, soundButton);
        this.setRoot(root);
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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
