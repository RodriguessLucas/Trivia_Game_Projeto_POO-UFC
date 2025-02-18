package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;

import java.io.IOException;

public class ConfiguracaoView implements Observer {
    private Model model;
    private ConfiguracaoViewController controller;
    private Stage stage;

    public ConfiguracaoView(Model model) {
        this.model = model;
    }

    public void initConfiguracaoView(Stage stage) {
        this.stage = stage;
        model.adicionarObservador(this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaConfiguracao-view.fxml"));
            System.out.println(loader.toString());
            Parent root = loader.load();

            controller = loader.getController();
            controller.initConfiguracaoViewController(model, this);
            if(controller == null){
                System.out.println("esta null");
                throw new NullPointerException("esta null");
            }

            stage.setTitle("Configurações - Debug & Win");
            stage.setScene(new Scene(root, 650, 800));

            // Remove o observador ao fechar a tela
            stage.setOnCloseRequest(event -> removerObservador());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela de configurações.");
        }
    }

    @Override
    public void update() {
        System.out.println("ConfiguraçãoView: Configurações foram alteradas!");
        if (controller != null) {
            controller.atualizarValores();
        }
    }

    public void removerObservador() {
        model.removerObservador(this);
    }
}
