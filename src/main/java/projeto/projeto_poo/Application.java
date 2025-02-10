package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.controller.ScreenManager;
import javafx.scene.image.Image;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStage(stage);
        ScreenManager.mudarScene("/projeto/projeto_poo/view/application-view.fxml", 600, 800);

        stage.setMinWidth(400);
        stage.setMinHeight(500);

        stage.setTitle("Debug & Win");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/projeto/projeto_poo/view/images/icon.jpg")));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}