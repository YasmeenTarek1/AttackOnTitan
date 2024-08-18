package Controller;

import View.Scenes.CharactersScene;
import View.Scenes.ModeScene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;


public class CharactersSceneController {

    private final CharactersScene CharactersScene;
    private static String character;

    public CharactersSceneController(CharactersScene CharactersScene) {
        this.CharactersScene = CharactersScene;
        initialize();
    }

    public void initialize() {

        CharactersScene.getCh1().getButton().setOnAction(e -> {
            character = "Levi";
            ModeScene next = new ModeScene(new StackPane());
            ModeSceneController controller = new ModeSceneController(next);
            MainController.switchToScene(next);
        });
        CharactersScene.getCh2().getButton().setOnAction(e -> {
            character = "Armin";
            ModeScene next = new ModeScene(new StackPane());
            ModeSceneController controller = new ModeSceneController(next);
            MainController.switchToScene(next);
        });
        CharactersScene.getCh3().getButton().setOnAction(e -> {
            character = "Mikasa";
            ModeScene next = new ModeScene(new StackPane());
            ModeSceneController controller = new ModeSceneController(next);
            MainController.switchToScene(next);
        });
        CharactersScene.getCh4().getButton().setOnAction(e -> {
            character = "Eren";
            ModeScene next = new ModeScene(new StackPane());
            ModeSceneController controller = new ModeSceneController(next);
            MainController.switchToScene(next);
        });

        CharactersScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                MainController.closeWindow();
                System.exit(0);
            }
        });

    }

    public static String getCharacter() {
        return character;
    }
}
