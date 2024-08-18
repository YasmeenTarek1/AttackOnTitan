package Controller;

import View.Scenes.ModeScene;
import View.Scenes.VideoScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class ModeSceneController {

    private final ModeScene modeScene;
    private static String mode;
    private static int numOfLanes;

    public ModeSceneController(ModeScene modeScene) {
        this.modeScene = modeScene;
        initialize();
    }

    public void initialize() {

        modeScene.getButtonGame1().getButton().setOnAction(e -> {
            mode = "easy";
            numOfLanes = 3;
            VideoScene next = new VideoScene(new StackPane());
            VideoSceneController controller = new VideoSceneController(next);
            MainController.switchToScene(next);
        });

        modeScene.getButtonGame2().getButton().setOnAction(e -> {
            mode = "hard";
            numOfLanes = 5;
            VideoScene next = new VideoScene(new StackPane());
            VideoSceneController controller = new VideoSceneController(next);
            MainController.switchToScene(next);
        });

        modeScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });
    }

    public static String getMode() {
        return mode;
    }

    public static int getNumOfLanes() {
        return numOfLanes;
    }
}
