package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.EstatisticaJogador;
import projeto.projeto_poo.model.Jogador;

public class EstatisticasViewController implements Observer{
    @FXML private Label lblMaiorSequenciaAcertos;
    @FXML private Label lblAssuntoMaiorAcerto;
    @FXML private Label lblAssuntoMenorAcerto;
    @FXML private Label lblMaiorPontuacao;
    @FXML private Label lblTotalAcertos;
    @FXML private Label lblTotalErros;

    private Jogador jogador;
    private EstatisticasView view;

    public void initEstatisticasViewController(Jogador jogador, EstatisticasView view) {
        this.jogador = jogador;
        this.view = view;
        EstatisticaJogador.adicionarObservador(this);
        atualizarEstatisticas();
    }

    private void atualizarEstatisticas() {
        lblMaiorSequenciaAcertos.setText(String.valueOf(EstatisticaJogador.getMaiorSequenciaAcerto()));
        lblAssuntoMaiorAcerto.setText(EstatisticaJogador.getAssuntoMaiorAcerto());
        lblAssuntoMenorAcerto.setText(EstatisticaJogador.getAssuntoMenorAcerto());
        lblMaiorPontuacao.setText(String.valueOf(EstatisticaJogador.getMaiorPontuacao()));
        lblTotalAcertos.setText(String.valueOf(EstatisticaJogador.getTotalAcertos()));
        lblTotalErros.setText(String.valueOf(EstatisticaJogador.getTotalErros()));
    }

    @FXML
    public void voltarMenu() {
        //EstatisticaJogador.removerObservador(this);
       // TelaMenuView telaMenu = new TelaMenuView();
        //telaMenu.initTelaMenuView((Stage) lblMaiorSequenciaAcertos.getScene().getWindow(), jogador);
       // System.out.println("Voltando para a tela do menu.");
    }

    @Override
    public void update() {
        atualizarEstatisticas();
    }


}

