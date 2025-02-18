package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;

import java.io.IOException;

public class TelaMenuView implements Observer {
    private Model model;
    private TelaMenuViewController controller;
    private Stage stage;

    public void initTelaMenuView(Stage stage, Model model) {
        this.model = model;
        this.stage = stage;
        model.adicionarObservador(this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaMenu-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            if (controller != null) {
                controller.initTelaMenuViewController(model, this);
            } else {
                System.out.println("Erro: controlador não encontrado!");
            }

            // Configura a cena e exibe a janela
            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Erro ao abrir tela menu");
        }
    }

    @Override
    public void update() {
        System.out.println("TelaMenuView: Nome do jogador atualizado para " + model.getJogador().getNome());
        if (controller != null) {
            controller.setMensagem(model.getJogador().getNome());
        }
    }
}
