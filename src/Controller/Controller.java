package Controller;

import View.BattleGUI;
import View.OpenScene;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Controller extends Application {
    public static Stage window = new Stage();
    public static BattleGUI battleGUI;
    public static MediaPlayer music;

    @Override
    public void start(Stage primarystage) throws Exception {
    	Media song = new Media(new File("Media/Sounds/Open Track.mp3").toURI().toString());
        music = new MediaPlayer(song);

        MediaView mediaView = new MediaView(music);
        mediaView.getMediaPlayer().play();
        mediaView.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);

        window = primarystage;
        
        primarystage.setFullScreenExitHint("");
        primarystage.setScene(new OpenScene(new StackPane()));
        primarystage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
