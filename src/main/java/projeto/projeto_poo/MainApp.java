package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.model.QuizModel;
import projeto.projeto_poo.view.TelaInicialView;

public class MainApp extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        QuizModel model = new QuizModel();
        TelaInicialView telaInicial = new TelaInicialView();
        telaInicial.initTelaInicialView(primaryStage, model);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
