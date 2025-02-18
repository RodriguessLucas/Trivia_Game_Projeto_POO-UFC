package projeto.projeto_poo.view;

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

    public TelaMenuView(Model model) {
        this.model = model;
    }

    public void initTelaMenuView(Stage stage) {
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
            stage.setOnCloseRequest(event -> removerObservador());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Erro ao abrir tela menu");
        }
    }

    public void removerObservador() {
        model.removerObservador(this);
    }

    @Override
    public void update() {}
}
