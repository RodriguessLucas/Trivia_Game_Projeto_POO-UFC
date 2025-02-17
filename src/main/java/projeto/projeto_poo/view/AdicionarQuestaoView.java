package projeto.projeto_poo.view;

import projeto.projeto_poo.model.Jogador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class AdicionarQuestaoView  {
    private Stage stage;
    private AdicionarQuestaoViewController controller;


    public void initAdicionarQuestaoView(Stage stage, Jogador jogador) {
        if (stage == null) {
            System.out.println("Erro: Stage está NULL ao iniciar AdicionarQuestaoView.");
            return;
        }

        this.stage = stage;


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/telaAdicionarQuestao-view.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.initAdicionarQuestaoViewController(jogador, this);


            stage.setTitle("Debug & Win");
            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir a tela de adicionar questão.");
        }
    }

}
