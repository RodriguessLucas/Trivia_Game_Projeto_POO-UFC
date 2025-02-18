package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.QuizModel;

import java.io.IOException;

public class TelaMenuView implements Observer {

    private static final String FXML_PATH = "/projeto/projeto_poo/view/telaMenu-view.fxml";
    private static final String WINDOW_TITLE = "Debug & Win";

    private QuizModel model;
    private Jogador jogador;
    private TelaMenuViewController controller;
    private Stage stage;

    @Override
    public void update() {
        System.out.println("TelaMenuView: Nome do jogador atualizado para " + jogador.getNome());
        if (controller != null) {
            controller.setMensagem(jogador.getNome());
        }
    }

    public void initTelaMenuView(Stage stage, Jogador jogador) {
        this.stage = stage;
        this.jogador = jogador;

        jogador.attachObserver(this);

        Parent root = carregarFXML();
        if (root != null) {
            configurarJanela(root);
        }
    }

    private Parent carregarFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH));
            Parent root = loader.load();

            controller = loader.getController();
            if (controller == null) {
                System.err.println("Erro: Controlador não foi carregado corretamente!");
                return null;
            }
            controller.initTelaMenuViewController(jogador, this);

            return root;
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo FXML de tela menu: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void configurarJanela(Parent root) {
        stage.setTitle(WINDOW_TITLE);
        stage.setScene(new Scene(root, 650, 800));
        stage.show();
    }
}