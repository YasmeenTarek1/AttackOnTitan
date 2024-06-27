package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class VideoScene extends Scene {
    StackPane root;

    public VideoScene(StackPane p) {
        super(p, 1550, 870);
        root = new StackPane();
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        Media media = new Media(new File("Media/Sounds/Video.mp4").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);    // MediaPlayer to play the Media
        MediaView mediaView = new MediaView(mediaPlayer);    // MediaView to display the video


        mediaView.setFitWidth(1550);
        mediaView.setFitHeight(870);
        mediaView.setPreserveRatio(true);

        ProgressBar progressBar = new ProgressBar();
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

        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 1000; i++) {
                    Thread.sleep(16);
                    int finalI = i;
                    Platform.runLater(() -> progressBar.setProgress( (finalI) / 1000.0));
                }
                return null;
            }
        };

        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();


        root.getChildren().addAll(mediaView , ProgressBar);
        this.setRoot(root);
        Controller.music.stop();
        mediaPlayer.play();


        mediaPlayer.setOnEndOfMedia(() -> {
            Scene nxt = new BattleGUI(new StackPane());
            Controller.window.setScene(nxt);
        });
        setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                Scene nxt = new BattleGUI(new StackPane());
                Controller.window.setScene(nxt);
                mediaPlayer.stop();
            }
        });
    }
}
