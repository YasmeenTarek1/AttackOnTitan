package Controller;

import View.Scenes.CharactersScene;
import View.Scenes.CreditsScene;
import View.Scenes.RulesScene;
import View.Scenes.StartMenuScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class StartMenuSceneController {

    private final StartMenuScene startMenuScene;

    public StartMenuSceneController(StartMenuScene startMenuScene) {
        this.startMenuScene = startMenuScene;
        initialize();
    }

    public void initialize() {
        startMenuScene.getButtonGame1().getButton().setOnAction(
                e -> {
                    CharactersScene nextScene = new CharactersScene(new StackPane());
                    CharactersSceneController controller = new CharactersSceneController(nextScene);
                    MainController.switchToScene(nextScene);
                });

        startMenuScene.getButtonGame2().getButton().setOnAction(
                e -> {
                    RulesScene nextScene = new RulesScene(new StackPane());
                    RulesSceneController controller = new RulesSceneController(nextScene);
                    MainController.switchToScene(nextScene);
                });

        startMenuScene.getButtonGame3().getButton().setOnAction(
                e -> {
                    CreditsScene nextScene = new CreditsScene(new StackPane());
                    CreditsSceneController controller = new CreditsSceneController(nextScene);
                    MainController.switchToScene(nextScene);
                });

        startMenuScene.getButtonGame4().getButton().setOnAction(e -> MainController.closeWindow());

        startMenuScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });

        startMenuScene.getSoundButton().getButton().setOnAction(e -> {   // there is sound, so mute it
            MainController.pauseMusic();
            startMenuScene.root.getChildren().remove(startMenuScene.getSoundButton());
            startMenuScene.root.getChildren().add(startMenuScene.getMuteButton());
            startMenuScene.getMuteButton().setTranslateX(1430);
        });
        startMenuScene.getMuteButton().getButton().setOnAction(e -> { // there is mute, so turn it on
            MainController.continueMusic();
            startMenuScene.root.getChildren().remove(startMenuScene.getMuteButton());
            startMenuScene.root.getChildren().add(startMenuScene.getSoundButton());
            startMenuScene.getSoundButton().setTranslateX(1430);
        });

    }

}
