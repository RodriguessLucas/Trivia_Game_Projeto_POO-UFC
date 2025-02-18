package projeto.projeto_poo;

import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;
import projeto.projeto_poo.view.TelaInicialView;

public class MainApp extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        Model model = Model.getInstancia();


        TelaInicialView telaInicial = new TelaInicialView(model);
        model.adicionarObservador(telaInicial);
        telaInicial.initTelaInicial(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
