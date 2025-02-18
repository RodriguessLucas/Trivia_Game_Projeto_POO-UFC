package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.QuizModel;

import java.io.IOException;

public class DebugWinView {
    private QuizModel model;
    private DebugWinViewController controller;
    private Stage stage;

    public DebugWinView() {
    }



    public void initialize(Stage stage, QuizModel model, Dificuldade dificuldade, Assunto assunto, int qntdQuestoes) {
        this.stage = stage;
        loadScene(dificuldade, assunto, qntdQuestoes, model);
    }

    private void loadScene( Dificuldade dificuldade, Assunto assunto, int qntdQuestoes, QuizModel Model) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaDebugWin-view.fxml"));
            Parent root = loader.load();
            controller = loader.getController();

            if (dificuldade == null || assunto == null || qntdQuestoes == 0) {
                controller.initializeController(null, null, 0, model);
            } else {
                controller.initializeController(dificuldade, assunto, qntdQuestoes, model);
            }

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do jogo.");
        }
    }
}