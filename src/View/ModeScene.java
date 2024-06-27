package View;

import Controller.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ModeScene extends Scene {

    private StackPane root;
    private final HBox menuBox = new HBox(110);
    private ButtonGame ButtonGame1, ButtonGame2;
    public static String mode;
    public static int numOfLanes;

    public ModeScene(StackPane p) {
        super(p, 1550, 870);

        root = new StackPane();
        createContent();

        this.setRoot(root);
        this.setCursor(new ImageCursor(new Image("file:Media/Visuals/Cursor.png")));

        ButtonGame1.getButton().setOnAction(e -> {
            mode = "easy";
            numOfLanes = 3;
            Scene nxt = new VideoScene(new StackPane());
            Controller.window.setScene(nxt);

        });
        ButtonGame2.getButton().setOnAction(e -> {
            mode = "hard";
            numOfLanes = 5;
            Scene nxt = new VideoScene(new StackPane());
            Controller.window.setScene(nxt);
        });
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                Controller.window.close();
                System.exit(0);
            }
        });
    }

    private void setBackground(Image img) {
        ImageView backgroundImageView = new ImageView(img);
        BoxBlur blur = new BoxBlur(7, 7, 25); // Adjust parameters for desired blur intensity
        backgroundImageView.setEffect(blur);
        backgroundImageView.setFitWidth(1550);
        backgroundImageView.setFitHeight(870);
        root.getChildren().add(backgroundImageView);
    }

    private void createContent() {

        Image img = new Image("file:Media/Visuals/Background 2.jpg");
        setBackground(img);

        ImageView easy = new ImageView(new Image("file:Media/Visuals/Easy Mode.png"));
        ImageView hard = new ImageView(new Image("file:Media/Visuals/Hard Mode.png"));

        easy.setFitWidth(400);
        easy.setFitHeight(600);
        easy.setTranslateX(-300);
        easy.setTranslateY(-70);

        hard.setFitWidth(400);
        hard.setFitHeight(600);
        hard.setTranslateX(320);
        hard.setTranslateY(-70);

        DropShadow glow = new DropShadow();         // glowing border
        glow.setColor(Color.LIGHTBLUE);
        glow.setWidth(50);                          // width of the glow
        glow.setHeight(50);                         // height of the glow
        glow.setSpread(0.5);

        easy.setEffect(glow);
        hard.setEffect(glow);

        ButtonGame1 = new ButtonGame(new Image("file:Media/Visuals/Easy Button.png"), 500, 130);
        ButtonGame2 = new ButtonGame(new Image("file:Media/Visuals/Hard Button.png"), 500, 130);

        menuBox.setTranslateX(180);
        menuBox.setTranslateY(700);
        menuBox.getChildren().addAll(ButtonGame1, ButtonGame2);

        root.getChildren().addAll(menuBox, easy, hard);

        startAnimation();
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

}
