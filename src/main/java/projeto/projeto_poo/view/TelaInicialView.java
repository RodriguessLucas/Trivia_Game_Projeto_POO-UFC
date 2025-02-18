package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;

import java.io.IOException;


public class TelaInicialView implements Observer {
    private Model model;
    private Jogador jogador;
    private TelaInicialViewController controller;
    private Stage stage;

    public void handleEvent(Stage stage, Jogador jogador, Model model) {
        controller = new TelaInicialViewController();
        this.jogador = jogador;
        this.model = model;
        this.stage = stage;
        model.getJogador().setNome(jogador.getNome());
        model.adicionarObservador(this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaInicial-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initTelaInicialViewController(model, this);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir tela inicial");
        }
    }

    @Override
    public void update() {

        // Atualiza a interface gráfica caso o Model mude
        System.out.println("TelaInicialView: Nome do jogador atualizado para " + jogador.getNome());
    }

    public void exibeMSG(String msg) {
        System.out.println("\n" + msg + "\n");
    }
}
