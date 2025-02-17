package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ConfiguracaoView implements Observer {
    private Configuracoes configuracoes;
    private ConfiguracaoViewController controller;
    private Stage stage;

    public void initConfiguracaoView(Stage stage, Jogador jogador) {
        this.configuracoes = Configuracoes.getInstancia(); // Usa Singleton para garantir que é único
        this.stage = stage;

        // Registra a view como observador das mudanças nas configurações
        configuracoes.attachObserver(this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaConfiguracoes-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initConfiguracaoViewController(configuracoes, this, jogador);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
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


}
