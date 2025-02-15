package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class EstatisticasView {
    private Jogador jogador;
    private EstatisticasViewController controller;
    private Stage stage;

    public void initEstatisticasView(Stage stage, Jogador jogador) {
        this.jogador = jogador;
        this.stage = stage;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaEstatisticas-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initEstatisticasViewController(jogador, this);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir tela de estatísticas.");
        }
    }
}
