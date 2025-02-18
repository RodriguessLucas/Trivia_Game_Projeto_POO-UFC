package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.QuizModel;

import java.io.IOException;

public class ConfiguracaoView implements Observer {

    private static final int WINDOW_WIDTH = 650;
    private static final int WINDOW_HEIGHT = 800;

    private QuizModel model;
    private Configuracoes configuracoes;
    private Stage stage;
    private ConfiguracaoViewController configuracoesController;

    public void initConfiguracaoView(Stage stage, Configuracoes configuracoes, Jogador jogador) {
        this.configuracoes = configuracoes;
        this.stage = stage;

        // Registra a ConfiguracaoView como observadora das configurações.
        model.attachObserver(this);

        // Configura a cena (FXML).
        setupScene(jogador);
    }

    private void setupScene(Jogador jogador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaConfiguracoes-view.fxml"));
            Parent root = loader.load();
            configuracoesController = loader.getController();

            configuracoesController.initConfiguracaoViewController(configuracoes, this, jogador);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela de configurações: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        System.out.println("ConfiguraçãoView: Configurações foram alteradas!");
        if (configuracoesController != null) {
            configuracoesController.atualizarValores();
        }
    }
}