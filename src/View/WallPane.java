package View;

import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;


public class WallPane extends VBox {
    private ButtonGame b;
    private GridPane weapons;
    public ProgressBar hpBar;
    public boolean fire = false;
    private int end;
    private int i = 0, j = 0; // Initial row and column index
    private ImageView fire1 , fire2 , fire3;

    public WallPane(Image img, int w, int h, int x, int y) {
        b = new ButtonGame(img, w, h);

        setMinWidth(w);
        setMaxWidth(w);
        setMinHeight(h);
        setMaxHeight(h);
        setTranslateX(x);
        setTranslateY(y);
        setSpacing(10);

        weapons = new GridPane();
        weapons.setVgap(0);
        weapons.setHgap(0);

        Rotate r = new Rotate(-35, Rotate.Z_AXIS);
        Scale s = new Scale(1, -1); // Flip vertically
        weapons.getTransforms().addAll(r, s);

        // Wrap GridPane in a StackPane to ensure it stays fixed
        StackPane weaponsContainer = new StackPane(weapons);
        weaponsContainer.setMinSize(200, 200);
        weaponsContainer.setMaxSize(200, 200);

        if(BattleGUI.b.getOriginalLanes().size() == 3) {
            weaponsContainer.setTranslateX(80);
            weaponsContainer.setTranslateY(90);
            end = 2;
        }
        else{
            weaponsContainer.setTranslateX(110);
            weaponsContainer.setTranslateY(85);
            end = 1;
        }

        hpBar = new ProgressBar();
        hpBar.setProgress(1);
        hpBar.setPrefWidth(120);
        hpBar.setPrefHeight(17);
        hpBar.setStyle("-fx-accent: green;");
        hpBar.setTranslateY(100);
        hpBar.setTranslateX(80);

        // Ensure hpBar size is fixed
        hpBar.setMinWidth(120);
        hpBar.setMinHeight(17);

        getChildren().addAll(b, hpBar, weaponsContainer);
    }

    public void addweapon(WeaponPane weapon) {

        // vertical flip to counteract the GridPane flip (To expand up not down)
        Scale flipBack = new Scale(1, -1);
        weapon.getTransforms().add(flipBack);

        weapons.add(weapon, i, j);

        if (i == end) {
            i = 0;
            j++;
        } else {
            i++;
        }

        // Ensure hpBar is not affected by GridPane changes
        hpBar.setMinWidth(120);
        hpBar.setMinHeight(17);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), weapon);
        translateTransition.setFromY(-50); // Start above
        translateTransition.setToY(100); // Move to the normal position
        translateTransition.play();
    }
    public void setEffectEND(){ // Giant Fire when the lane is lost
        getChildren().remove(fire1);
        getChildren().remove(fire2);
        if(BattleGUI.b.getOriginalLanes().size() == 3)
            getChildren().remove(fire3);

        ImageView fire = new ImageView(new Image("file:Media/Visuals/Fire Effect.gif"));

        if(BattleGUI.b.getOriginalLanes().size() == 3) {
            fire.setFitWidth(280);
            fire.setFitHeight(230);
            fire.setTranslateX(-15);
            fire.setTranslateY(-265);
        }
        else {
            fire.setFitWidth(200);
            fire.setFitHeight(150);
            fire.setTranslateX(5);
            fire.setTranslateY(-230);
        }
        getChildren().add(fire);
    }
    public void setEffectMID(){  // Small Fires when the lane's hp < 0.5

        fire1 = new ImageView(new Image("file:Media/Visuals/Fire Effect.gif"));
        fire2 = new ImageView(new Image("file:Media/Visuals/Fire Effect.gif"));
        fire3 = new ImageView(new Image("file:Media/Visuals/Fire Effect.gif"));

        if(BattleGUI.b.getOriginalLanes().size() == 3) {
            fire1.setFitWidth(50);
            fire1.setFitHeight(50);

            fire2.setFitWidth(65);
            fire2.setFitHeight(65);

            fire3.setFitWidth(40);
            fire3.setFitHeight(40);

            fire1.setTranslateX(40);
            fire1.setTranslateY(-160);

            fire2.setTranslateX(100);
            fire2.setTranslateY(-180);

            fire3.setTranslateX(150);
            fire3.setTranslateY(-360);

            getChildren().addAll(fire1 , fire2 , fire3);
        }
        else{
            fire1.setFitWidth(40);
            fire1.setFitHeight(40);

            fire2.setFitWidth(50);
            fire2.setFitHeight(50);

            fire1.setTranslateX(30);
            fire1.setTranslateY(-155);

            fire2.setTranslateX(60);
            fire2.setTranslateY(-305);

            getChildren().addAll(fire1 , fire2);
        }
    }
}
