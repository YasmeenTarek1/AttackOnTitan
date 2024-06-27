package View;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TitanPane extends VBox {
    public ImageView titan , Explosion;
    public Image img;
    public ProgressBar hpBar;

    public TitanPane(Image img, int w, int h) {
        this.img = img;
        hpBar = new ProgressBar();
        hpBar.setPrefWidth(100);
        hpBar.setPrefHeight(15);
        hpBar.setStyle("-fx-accent: green;");
        hpBar.setTranslateY(-85);
        hpBar.setTranslateX(-10);

        titan = new ImageView(img);
        titan.setFitWidth(w);
        titan.setFitHeight(h);
        setAlignment(Pos.CENTER_LEFT); // Align titan to the left within the HBox
        getChildren().addAll(titan, hpBar);
    }

    public void moveTitan(int x) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(titan);
        translate.setDuration(Duration.millis(1250));
        translate.setByX(x * 50);                       // just change the x to move them
        translate.setByY(-3);
        translate.play();

        translate = new TranslateTransition();
        translate.setNode(hpBar);
        translate.setDuration(Duration.millis(1250));
        if (this.img.getUrl().equals("file:Media/Visuals/Colossal Titan.png")) {
            translate.setByX(x*40);
            translate.setByY(28);
            translate.play();
        }
        else {
            translate.setByX(x * 40);
            if (this.img.getUrl().equals( "file:Media/Visuals/Abnormal Titan.png"))
                translate.setByY(65);
            else
                translate.setByY(50);
            translate.play();
        }
    }

    public void setEffect() {
        Explosion = new ImageView(new Image("file:Media/Visuals/Explotion Effect.gif"));
        Explosion.setFitWidth(90);
        Explosion.setFitHeight(90);

        // Use localToParent to get the titan's position relative to its parent
        Bounds bounds = titan.localToParent(titan.getBoundsInLocal());
        Explosion.setTranslateX(bounds.getMinX());
        Explosion.setTranslateY(bounds.getMinY()-10);

        StackPane overlayPane = new StackPane(titan, Explosion);
        getChildren().clear();
        getChildren().addAll(overlayPane);
    }
}