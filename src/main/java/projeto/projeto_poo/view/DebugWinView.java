package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Model;

import java.io.IOException;

public class DebugWinView implements Observer {
    private Model model;
    private DebugWinViewController controller;
    private Stage stage;

    public DebugWinView(Model model) {
        this.model = model;
    }

    public void initDebugWinView(Stage stage) {
        this.stage = stage;
        model.adicionarObservador(this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaDebugWin-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initDebugWinViewController(model, this);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.setOnCloseRequest(event -> removerObservador());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do jogo.");
        }
    }

    public void removerObservador() {
        model.removerObservador(this);
    }

    @Override
    public void update() {
        if (controller != null) {
            controller.atualizarQuestao();
        }
    }
}
