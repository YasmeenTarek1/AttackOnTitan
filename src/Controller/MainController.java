package Controller;

import View.Scenes.BattleGUI;
import View.Scenes.OpenScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MainController extends Application {
    private static Stage window = new Stage();
    private static BattleGUI battleScene;
    private static MediaPlayer music;

    @Override
    public void start(Stage primarystage) throws Exception {
        window = primarystage;

        OpenScene openScene = new OpenScene(new StackPane());
        OpenSceneController openSceneController = new OpenSceneController(openScene);
        switchToScene(openScene);

        window.show();
    }

    public static void switchToScene(Scene newScene) {
        window.setScene(newScene);
    }

    public static void closeWindow() {
        window.close();
    }

    public static void playMusic(String path) {
        if (music != null)
            music.stop();
        Media media = new Media(new File(path).toURI().toString());
        music = new MediaPlayer(media);
        music.play();
    }

    public static void pauseMusic() {
        music.pause();
    }

    public static void continueMusic() {
        music.play();
    }

    public static void stopMusic() {
        music.stop();
    }

    public static Stage getWindow() {
        return window;
    }

    public static MediaPlayer getMusic() {
        return music;
    }

    public static BattleGUI getBattleScene() {
        return battleScene;
    }

    public static void setBattleScene(BattleGUI battleScene) {
        MainController.battleScene = battleScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
