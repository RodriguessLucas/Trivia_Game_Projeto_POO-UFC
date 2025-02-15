package projeto.projeto_poo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;

import java.io.IOException;

public class DebugWinView {
    private DebugWinViewController controller; // aqui pode dar erro sepa
    private Stage stage;

    public DebugWinView(Stage stage, Dificuldade dificuldade, Configuracoes config, boolean ehAleatorio ) {
        this.stage = stage;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaDebugWin-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initDebugWinViewController(dificuldade, config, ehAleatorio);

            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela do jogo.");
        }

        /* OBS tem que ver se vale apenas deixar a chamada das questoes no modo aleatorio e definida no mesmo metodo
        ou criar dois metodos para os casos especificos
         */

    }
}
