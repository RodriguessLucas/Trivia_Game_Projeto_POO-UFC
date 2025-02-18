package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.QuizModel;

import java.io.IOException;

public class AdicionarQuestaoView {
    private static final String TELA_TITULO = "Debug & Win";
    private static final int SCENE_LARGURA = 650;
    private static final int SCENE_ALTURA = 800;

    private QuizModel model;
    private Stage stage;
    private AdicionarQuestaoViewController adicionarQuestaoController;

    public void inicializar(Stage stage, Jogador jogador) {
        if (stage == null) {
            System.err.println("Erro: Stage está NULL ao iniciar AdicionarQuestaoView.");
            return;
        }
        this.stage = stage;

        Parent root = carregarFXML("/projeto/projeto_poo/view/telaAdicionarQuestao-view.fxml");
        if (root == null) return;

        adicionarQuestaoController.initAdicionarQuestaoViewController(jogador, this);

        stage.setTitle(TELA_TITULO);
        stage.setScene(new Scene(root, SCENE_LARGURA, SCENE_ALTURA));
        stage.show();
    }

    private Parent carregarFXML(String caminhoFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            this.adicionarQuestaoController = loader.getController();
            return root;
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + e.getMessage());
            return null;
        }
    }
}