package View.Scenes;

import View.GameElements.ButtonGame;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class CharactersScene extends Scene {

    public final StackPane root = new StackPane();
    private final HBox menuBox = new HBox(95);
    private final ButtonGame ch1;
    private final ButtonGame ch2;
    private final ButtonGame ch3;
    private final ButtonGame ch4;

    public CharactersScene(Pane p) {
        super(p, 1550, 870);

        setBackground(new Image("file:Media/Visuals/Background 2.jpg"));
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        ImageView viewLevi = new ImageView(new Image("file:Media/Visuals/Levi.png"));
        ImageView viewArmin = new ImageView(new Image("file:Media/Visuals/Armin.png"));
        ImageView viewMikasa = new ImageView("file:Media/Visuals/Mikasa.png");
        ImageView viewEren = new ImageView("file:Media/Visuals/Eren.png");

        viewEren.setFitHeight(510);
        viewEren.setFitWidth(350);

        viewArmin.setFitHeight(510);
        viewArmin.setFitWidth(350);

        viewMikasa.setFitHeight(510);
        viewMikasa.setFitWidth(350);

        viewLevi.setFitHeight(510);
        viewLevi.setFitWidth(350);

        DropShadow glow = new DropShadow();                     // glowing border
        glow.setColor(Color.LIGHTBLUE);
        glow.setWidth(30);
        glow.setHeight(30);
        glow.setSpread(0.5);

        viewLevi.setEffect(glow);
        viewEren.setEffect(glow);
        viewArmin.setEffect(glow);
        viewMikasa.setEffect(glow);

        HBox images = new HBox(30);
        images.getChildren().addAll(viewLevi, viewArmin, viewMikasa, viewEren);
        images.setTranslateX(22);
        images.setTranslateY(40);

        ch1 = new ButtonGame(new Image("file:Media/Visuals/Levi Button.png"), 400, 130);
        ch2 = new ButtonGame(new Image("file:Media/Visuals/Armin Button.png"), 400, 130);
        ch3 = new ButtonGame(new Image("file:Media/Visuals/Mikasa Button.png"), 400, 130);
        ch4 = new ButtonGame(new Image("file:Media/Visuals/Eren Button.png"), 400, 130);

        ch1.setTranslateX(20);
        ch2.setTranslateX(10);
        ch3.setTranslateX(-10);
        ch4.setTranslateX(-40);

        menuBox.setTranslateX(-70);
        menuBox.setTranslateY(600);
        menuBox.getChildren().addAll(ch1, ch2, ch3, ch4);

        root.getChildren().addAll(images, menuBox);

        startAnimation();
        this.setRoot(root);
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        BoxBlur blur = new BoxBlur(7, 7, 25); // Adjust parameters for desired blur intensity
        backgroundImageView.setEffect(blur);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }


    private void startAnimation() {
        SequentialTransition seqTransition = new SequentialTransition(); // combine multiple animations

        for (int i = 0; i < menuBox.getChildren().size(); i++) {
            Node button = menuBox.getChildren().get(i);

            button.setOpacity(0); // hidden

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), button); // gradually increase its opacity
            fadeTransition.setToValue(1); // visible
            if (i == 0)
                fadeTransition.setDelay(Duration.seconds(0.5));
            else
                fadeTransition.setDelay(Duration.seconds(0.07 * i)); // Delay each button's animation

            seqTransition.getChildren().add(fadeTransition);
        }

        seqTransition.play();
    }

    public ButtonGame getCh1() {
        return ch1;
    }

    public ButtonGame getCh2() {
        return ch2;
    }

    public ButtonGame getCh3() {
        return ch3;
    }

    public ButtonGame getCh4() {
        return ch4;
    }

}
