package View;

import java.io.File;

import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class CreditsScene extends Scene{

    public CreditsScene(Pane root) {
        super(root);
        Controller.music.stop();
     this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        Media media = new Media(new File("Media/Sounds/Credits Video.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView Video = new MediaView(mediaPlayer);

        mediaPlayer.play();

        Video.setFitWidth(1550);
        Video.setFitHeight(870);
        Video.setPreserveRatio(true);

        Media media2 = new Media(new File("Media/Sounds/Credits Music.mp3").toURI().toString());
        Controller.music = new MediaPlayer(media2);
        Controller.music.play();

        ButtonGame home = new ButtonGame(new Image("file:Media/Visuals/Home Button.png"), 90, 90);

        home.getButton().setOnAction(e -> {
            Controller.music.stop();
            Media song = new Media(new File("Media/Sounds/Open Track.mp3").toURI().toString());
            Controller.music = new MediaPlayer(song);
            Controller.music.play();
            Controller.window.setScene(new StartMenuScene(new StackPane()));
        });

        mediaPlayer.setOnEndOfMedia(() -> {
            Controller.music.stop();
            Media song = new Media(new File("Media/Sounds/Open Track.mp3").toURI().toString());
            Controller.music = new MediaPlayer(song);
            Controller.music.play();
            Controller.window.setScene(new StartMenuScene(new StackPane()));
        });

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Controller.window.close();
                System.exit(0);
            }
        });

        ButtonGame sound = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
        ButtonGame mute = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);

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

        root.getChildren().addAll(Video,home , sound);
        this.setRoot(root);
    }

}
