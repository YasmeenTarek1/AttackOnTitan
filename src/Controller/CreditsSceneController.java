package Controller;

import View.Scenes.CreditsScene;
import View.Scenes.StartMenuScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class CreditsSceneController {

    private final CreditsScene creditsScene;

    public CreditsSceneController(CreditsScene creditsScene) {
        this.creditsScene = creditsScene;
        MainController.playMusic("Media/Sounds/Credits Music.mp3");
        initialize();
    }

    public void initialize() {

        creditsScene.getHomeButton().getButton().setOnAction(e -> {
            MainController.playMusic("Media/Sounds/Open Track.mp3");
            StartMenuScene next = new StartMenuScene(new StackPane());
            StartMenuSceneController controller = new StartMenuSceneController(next);
            MainController.switchToScene(next);
        });

        creditsScene.getMediaPlayer().setOnEndOfMedia(() -> {
            MainController.playMusic("Media/Sounds/Open Track.mp3");
            StartMenuScene next = new StartMenuScene(new StackPane());
            StartMenuSceneController controller = new StartMenuSceneController(next);
            MainController.switchToScene(next);
        });

        creditsScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });

        creditsScene.getSoundButton().getButton().setOnAction(e -> {   // there is sound, so mute it
            MainController.pauseMusic();
            creditsScene.root.getChildren().remove(creditsScene.getSoundButton());
            creditsScene.root.getChildren().add(creditsScene.getMuteButton());
            creditsScene.getMuteButton().setTranslateX(1430);
        });
        creditsScene.getMuteButton().getButton().setOnAction(e -> { // there is mute, so turn it on
            MainController.continueMusic();
            creditsScene.root.getChildren().remove(creditsScene.getMuteButton());
            creditsScene.root.getChildren().add(creditsScene.getSoundButton());
            creditsScene.getSoundButton().setTranslateX(1430);
        });

    }
}
