package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.controller.ScreenManager;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStage(stage);
        ScreenManager.mudarScene("/projeto/projeto_poo/view/application-view.fxml", 600, 800);
    }

    public static void main(String[] args) {
        launch();
    }
}