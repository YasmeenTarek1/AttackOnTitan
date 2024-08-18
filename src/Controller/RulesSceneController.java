package Controller;

import View.Scenes.RulesScene;
import View.Scenes.StartMenuScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class RulesSceneController {

    private final RulesScene rulesScene;

    public RulesSceneController(RulesScene rulesScene) {
        this.rulesScene = rulesScene;
        initialize();
    }

    public void initialize() {

        rulesScene.getHomeButton().getButton().setOnAction(e -> {
            StartMenuScene next = new StartMenuScene(new StackPane());
            StartMenuSceneController controller = new StartMenuSceneController(next);
            MainController.switchToScene(next);
        });

        rulesScene.getSoundButton().getButton().setOnAction(e -> {   // there is sound, so mute it
            MainController.pauseMusic();
            rulesScene.root.getChildren().remove(rulesScene.getSoundButton());
            rulesScene.root.getChildren().add(rulesScene.getMuteButton());
            rulesScene.getMuteButton().setTranslateX(1430);
        });

        rulesScene.getMuteButton().getButton().setOnAction(e -> { // there is mute, so turn it on
            MainController.continueMusic();
            rulesScene.root.getChildren().remove(rulesScene.getMuteButton());
            rulesScene.root.getChildren().add(rulesScene.getSoundButton());
            rulesScene.getSoundButton().setTranslateX(1430);
        });

        rulesScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });

    }
}
