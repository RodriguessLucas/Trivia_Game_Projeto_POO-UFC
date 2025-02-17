package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;

import java.io.IOException;

public class DebugWinView {
    private DebugWinViewController controller; // aqui pode dar erro sepa
    private Stage stage;

    public DebugWinView() {}

    public void initDebugWinView(Stage stage, Configuracoes config) {
        this.stage = stage;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaDebugWin-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initDebugWinViewController(config);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do jogo.");
        }
    }

    public void initDebugWinView(Stage stage, Configuracoes config, Dificuldade dificuldade, Assunto assunto, int qntdQuestoes) {
        this.stage = stage;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaDebugWin-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initDebugWinViewController(dificuldade, assunto, config, qntdQuestoes);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do jogo.");
        }

    }
}
