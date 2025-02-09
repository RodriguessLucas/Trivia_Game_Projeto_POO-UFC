package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.controller.ScreenManager;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStage(stage);
        ScreenManager.mudarScene("/projeto/projeto_poo/view/application-view.fxml", 600, 800);

        /*FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Quiz Game");
        stage.setScene(scene);
        stage.show();
         */
    }

    public static void main(String[] args) {
        launch();
    }
}