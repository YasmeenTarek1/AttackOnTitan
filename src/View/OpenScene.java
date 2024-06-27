package View;

import Controller.Controller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OpenScene extends Scene {
    StackPane root;
    public OpenScene(StackPane p) {
        super(p, 1550, 870);

        root = new StackPane();
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        setBackground(new Image("file:Media/Visuals/Background 1.jpg"));

        Label messageLabel = new Label("Press Enter to continue");
        messageLabel.setFont(Font.font(32));
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setTranslateY(397);

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Controller.window.close();
                System.exit(0);
            }
        });

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setStyle("-fx-accent:rgb(155,11,11);");
        progressBar.setPrefSize(350 , 20);
        progressBar.setTranslateY(400);


        Task<Void> task = new Task() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(25);
                    int finalI = i;
                    Platform.runLater(() -> progressBar.setProgress( (finalI) / 100.0));
                }
                return null;
            }
        };

        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();

        task.setOnSucceeded(event -> {
            Scene nxt = new StartMenuScene(new StackPane());
            Controller.window.setScene(nxt);
        });
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE)
                Controller.window.close();
        });

        root.getChildren().addAll(new ImageView(new Image("file:Media/Visuals/Open Scene Logo.png")), progressBar);
        this.setRoot(root);

    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }
}
