package Controller;

import View.Scenes.OpenScene;
import View.Scenes.StartMenuScene;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class OpenSceneController {
    private final OpenScene openScene;

    public OpenSceneController(OpenScene openScene) {
        this.openScene = openScene;
        MainController.playMusic("Media/Sounds/Open Track.mp3");
        initialize();
    }

    private void initialize() {
        openScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(25);
                    int finalI = i;
                    Platform.runLater(() -> openScene.getProgressBar().setProgress(finalI / 100.0));
                }
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            StartMenuScene nextScene = new StartMenuScene(new StackPane());
            StartMenuSceneController controller = new StartMenuSceneController(nextScene);
            MainController.switchToScene(nextScene);
        });

        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();
    }
}
