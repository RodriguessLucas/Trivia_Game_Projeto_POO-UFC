package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.view.TelaInicialView;

public class MainApp extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {

        TelaInicialView telaInicial = new TelaInicialView();
        telaInicial.initTelaInicialView(primaryStage, new Jogador());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
