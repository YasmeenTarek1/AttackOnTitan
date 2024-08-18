package Controller;

import View.Scenes.BattleGUI;
import View.Scenes.VideoScene;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class VideoSceneController {

    private final VideoScene videoScene;

    public VideoSceneController(VideoScene videoScene) {
        this.videoScene = videoScene;
        MainController.stopMusic();
        initialize();
    }

    public void initialize() {

        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 1000; i++) {
                    Thread.sleep(16);
                    int finalI = i;
                    Platform.runLater(() -> videoScene.getProgressBar().setProgress((finalI) / 1000.0));
                }
                return null;
            }
        };

        Thread taskThread = new Thread(task);
        taskThread.setDaemon(true);
        taskThread.start();

        videoScene.getMediaPlayer().setOnEndOfMedia(() -> {
            BattleGUI next = new BattleGUI(new StackPane());
            MainController.switchToScene(next);
        });

        videoScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                videoScene.getMediaPlayer().stop();
                BattleGUI next = new BattleGUI(new StackPane());
                MainController.switchToScene(next);
            }
        });

    }
}
