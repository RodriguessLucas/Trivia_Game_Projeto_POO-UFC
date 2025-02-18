package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;

import java.io.IOException;

public class EstatisticasView {

    private static final String FXML_PATH = "/projeto/projeto_poo/view/telaEstatisticas-view.fxml";
    private EstatisticasViewController controller;
    private Stage stage;

    public void initialize(Stage stage, Jogador jogador) {
        this.stage = stage;
        loadScene(jogador);
    }

    private void loadScene(Jogador jogador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();
            this.controller = loader.getController();
            this.controller.inicializarController(jogador, this);
            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de estatísticas: " + e.getMessage());
        }
    }
}