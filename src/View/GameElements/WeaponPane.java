package View.GameElements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class WeaponPane extends HBox {
    ImageView view;
    int code;

    public WeaponPane(double w, double h, int code, int x, int y) {
        this.code = code;
        this.addInfo();

        view.setFitWidth(w);
        view.setFitHeight(h);

        setTranslateX(x);
        setTranslateY(y);

        getChildren().add(view);
    }

    public void addInfo() {
        switch (code) {
            case 1:
                view = new ImageView(new Image("file:Media/Visuals/Piercing Cannon.png"));
                break;
            case 3:
                view = new ImageView(new Image("file:Media/Visuals/Volley Spread Cannon.png"));
                break;
            case 2:
                view = new ImageView(new Image("file:Media/Visuals/Sniper Cannon.png"));
                break;
            default:
                view = new ImageView(new Image("file:Media/Visuals/Wall Trap.png"));
        }
    }
}
