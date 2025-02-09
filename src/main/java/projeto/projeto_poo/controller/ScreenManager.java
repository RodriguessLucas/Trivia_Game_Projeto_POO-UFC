package projeto.projeto_poo.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ScreenManager {

    private static Stage stage; // Janela principal

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void mudarScene(String fxmlFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            stage.setScene(new Scene(root, width, height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: " + fxmlFile);
        }
    }

    public static <T> T alterarTelaComController(String fxmlFile, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(fxmlFile));
            Parent root = loader.load();

            stage.setScene(new Scene(root, width, height));
            stage.show();

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela: " + fxmlFile);
            return null;
        }
    }
}

