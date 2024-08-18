package View.Scenes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class VideoScene extends Scene {

    public StackPane root = new StackPane();
    private final ProgressBar progressBar;
    private final MediaPlayer mediaPlayer;

    public VideoScene(StackPane p) {
        super(p, 1550, 870);
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        Media media = new Media(new File("Media/Sounds/Video.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView Video = new MediaView(mediaPlayer);

        Video.setFitWidth(1550);
        Video.setFitHeight(870);
        Video.setPreserveRatio(true);

        mediaPlayer.play();

        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setStyle("-fx-accent:rgb(0,0,0);");
        progressBar.setPrefSize(1500, 26);


        Label percentageLabel = new Label("0%");
        percentageLabel.setTextFill(Color.GREEN);
        percentageLabel.setFont(Font.font(18));
        percentageLabel.setStyle("-fx-font-weight: bold;");

        // Bind the label text to the progress bar's progress property
        percentageLabel.textProperty().bind(Bindings.concat(Bindings.format("%.0f", Bindings.multiply(progressBar.progressProperty(), 100)), "%"));

        StackPane ProgressBar = new StackPane(progressBar, percentageLabel);
        StackPane.setAlignment(percentageLabel, Pos.CENTER);

        ProgressBar.setTranslateY(400);
        ProgressBar.setTranslateY(400);

        root.getChildren().addAll(Video, ProgressBar);
        this.setRoot(root);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
