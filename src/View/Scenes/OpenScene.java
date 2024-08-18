package View.Scenes;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class OpenScene extends Scene {
    private final StackPane root = new StackPane();
    private final ProgressBar progressBar;

    public OpenScene(StackPane p) {
        super(p, 1550, 870);

        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        setBackground(new Image("file:Media/Visuals/Background 1.jpg"));

        progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setStyle("-fx-accent:rgb(155,11,11);");
        progressBar.setPrefSize(350, 20);
        progressBar.setTranslateY(400);

        root.getChildren().addAll(new ImageView(new Image("file:Media/Visuals/Open Scene Logo.png")), progressBar);
        this.setRoot(root);
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
