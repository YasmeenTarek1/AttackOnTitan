package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

public class LanePane extends Pane {
    public WallPane wall;
    public int spacing;
    public int Rotz;
    Label dangerLevel;
    StackPane DL;

    public LanePane(int w, int h, int x, int y, int spacing, int RotZ, Image WallImage, int wallWidth, int wallHeight, int wallX, int wallY , int DangerLevelX , int DangerLevelY) {
        this.spacing = spacing;
        this.Rotz = RotZ;
        dangerLevel = new Label();
        wall = new WallPane(WallImage, wallWidth, wallHeight, wallX, wallY);
        Rotate rotate1 = new Rotate(RotZ, Rotate.Z_AXIS);
        setMinWidth(w);
        setMaxWidth(w);
        setMinHeight(h);
        setMaxHeight(h);
        setTranslateX(x+200);
        setTranslateY(y+200);
        setOpacity(1);
        setStyle("-fx-background-color: rgba(172,11,11,0);");
        getTransforms().addAll(rotate1);
        DisplayDangerLevel(0 , DangerLevelX , DangerLevelY);
    }
    public void addTitan(VBox tt) {
        VBox h = new VBox(spacing);
        h.setAlignment(Pos.TOP_LEFT);
        Pane container = new Pane();
        int k = 0 , j = -100;
        for(int i = 0 ; i < tt.getChildren().size() ; i++) {                // to separate between hpBars of the adjacent titans
            ProgressBar b = ((TitanPane) tt.getChildren().get(i)).hpBar;
            b.setTranslateX(k);
            b.setTranslateY(j);
            k += 20;
            j -= 20;
            container.getChildren().add(b);
        }
        Rotate rotate1 = new Rotate(-35, Rotate.Z_AXIS);

        container.getTransforms().addAll(rotate1);
        h.getChildren().addAll(tt, container);
        getChildren().addAll(h);
    }

    public void DisplayDangerLevel(int d , int x , int y){
        ImageView img = new ImageView(new Image("file:Media/Visuals/Gold Button.png"));
        img.setFitWidth(100);
        img.setFitHeight(70);
        dangerLevel = new Label("DL : "+ d);
        dangerLevel.setFont(new Font(20));
        dangerLevel.setTextFill(Color.WHITE);

        Rotate rotate1 = new Rotate(-Rotz, Rotate.Z_AXIS);

        DL = new StackPane(img , dangerLevel);
        DL.getTransforms().addAll(rotate1);
        DL.setTranslateX(x);
        DL.setTranslateY(y);
        getChildren().add(DL);
    }

}
