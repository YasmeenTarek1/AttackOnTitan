package View;

import Controller.Controller;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class RulesScene extends Scene {
    private StackPane root;

    public RulesScene(StackPane p) {
        super(p);
        root = new StackPane();
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));
        setBackground(new Image("file:Media/Visuals/Background 2.jpg"));
        ImageView scroll = new ImageView(new Image("file:Media/Visuals/Game Rules.png"));
        scroll.setFitHeight(850);
        scroll.setFitWidth(1150);
        scroll.setTranslateX(-20);
        scroll.setTranslateY(-10);

        ButtonGame home = new ButtonGame(new Image("file:Media/Visuals/Home Button.png"), 90, 90);
        ButtonGame sound = new ButtonGame(new Image("file:Media/Visuals/Sound ON Button.png"), 120, 100);
        ButtonGame mute = new ButtonGame(new Image("file:Media/Visuals/Sound OFF Button.png"), 100, 100);

        sound.setTranslateX(1430);
        sound.setTranslateY(-11);

        home.getButton().setOnAction(e -> {
            Controller.window.setScene(new StartMenuScene(new StackPane()));
        });
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
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Controller.window.close();
                System.exit(0);
            }
        });

        root.getChildren().addAll(home, scroll , sound);
        this.setRoot(root);
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }

}