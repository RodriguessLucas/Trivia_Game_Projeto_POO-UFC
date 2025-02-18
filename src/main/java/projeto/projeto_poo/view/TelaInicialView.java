package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;
import projeto.projeto_poo.model.Jogador;

import java.io.IOException;

public class TelaInicialView implements Observer {
    private Model model;
    private Jogador jogador;
    private Stage stage;
    private TelaInicialViewController controller;

    public TelaInicialView(Model model) {
        this.model = model;
        this.jogador = model.getJogador();
    }

    public void initTelaInicial(Stage stage) {
        this.stage = stage;
        model.adicionarObservador(this);

        try {
            // Carregar FXML manualmente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaInicial-view.fxml"));
            Parent root = loader.load();

            // Obter o Controller manualmente e passar os dados necessários
            controller = loader.getController();
            controller.initTelaInicialViewController(model, this);

            // Configuração da cena
            Scene scene = new Scene(root, 650, 800);
            scene.getStylesheets().add(getClass().getResource("/projeto/projeto_poo/view/style/application-view.css").toExternalForm());

            stage.setTitle("Debug & Win");
            stage.setScene(scene);

            // Adiciona evento para remover Observer ao fechar a tela
            stage.setOnCloseRequest(event -> removerObservador());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir tela inicial");
        }
    }

    @Override
    public void update() {
        System.out.println("TelaInicialView: Nome do jogador atualizado para " + model.getJogador().getNome());
    }

    public void removerObservador() {
        model.removerObservador(this);
    }
}
