package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;

public class EstatisticasViewController implements Observer{
    @FXML private Label lblMaiorSequenciaAcertos;
    @FXML private Label lblAssuntoMaiorAcerto;
    @FXML private Label lblAssuntoMenorAcerto;
    @FXML private Label lblMaiorPontuacao;
    @FXML private Label lblTotalAcertos;
    @FXML private Label lblTotalErros;

    private Model model;
    private EstatisticasView view;

    public void initEstatisticasViewController(Model model, EstatisticasView view) {
        this.model = model;
        this.view = view;
        model.adicionarObservador(this);
        atualizarEstatisticas();
    }

    private void atualizarEstatisticas() {
        lblMaiorSequenciaAcertos.setText(String.valueOf(model.getMaiorSequenciaAcerto()));
        lblAssuntoMaiorAcerto.setText(model.getEstatisticaAssuntoMaiorAcerto());
        lblAssuntoMenorAcerto.setText(model.getEstatisticaAssuntoMenorAcerto());
        lblMaiorPontuacao.setText(String.valueOf(model.getEstatisticaMaiorPontuacao()));
        lblTotalAcertos.setText(String.valueOf(model.getEstatisticaTotalAcertos()));
        lblTotalErros.setText(String.valueOf(model.getEstatisticaTotalErros()));
    }

    @FXML
    public void voltarMenu() {
        Stage stageAtual = (Stage) lblMaiorSequenciaAcertos.getScene().getWindow();
        stageAtual.close();

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(new Stage());

        model.removerObservador(this);
        System.out.println("Voltando para a tela do menu.");
    }

    @Override
    public void update() {
        atualizarEstatisticas();
    }


}

