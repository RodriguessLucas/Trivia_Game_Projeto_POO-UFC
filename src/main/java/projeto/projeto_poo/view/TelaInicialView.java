package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.QuizModel;

import java.io.IOException;

public class TelaInicialView implements Observer {

    private  QuizModel model;
    private TelaInicialViewController controller;
    private Stage stage;

    // Renomeado para um nome mais representativo
    public void initTelaInicialView(Stage stage, QuizModel model) {
        this.stage = stage;
        configurarModelo(model);
        carregarInterface(stage, model);
    }

    private void configurarModelo(QuizModel model) {
        model.attachObserver(this);
    }

    private void carregarInterface(Stage stage, QuizModel model) {
        loadScene(stage, model);
    }

    // Método extraído para carregar a cena e atribuir o controlador
    private void loadScene(Stage stage, QuizModel model) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaInicial-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initialize(model, this);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            // Mensagem de erro mais clara
            System.err.println("Erro ao carregar a tela inicial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        // Atualiza a interface gráfica caso o Model mude
        System.out.println("TelaInicialView: Nome do jogador atualizado para " + model.getJogador().getNome());
    }

    public void exibeMensagem(String mensagem) {
        // Renomeação do método para melhor clareza
        System.out.println("\n" + mensagem + "\n");
    }
}