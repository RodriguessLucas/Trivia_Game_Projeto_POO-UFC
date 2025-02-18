package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.EstatisticaJogador;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.model.QuizModel;

public class EstatisticasViewController implements Observer {

    @FXML
    private Label lblMaiorSequenciaAcertos;
    @FXML
    private Label lblAssuntoMaiorAcerto;
    @FXML
    private Label lblAssuntoMenorAcerto;
    @FXML
    private Label lblMaiorPontuacao;
    @FXML
    private Label lblTotalAcertos;
    @FXML
    private Label lblTotalErros;

    private static final String MENSAGEM_VOLTAR_MENU = "Voltando para a tela do menu.";
    private Jogador jogador;
    private EstatisticasView view;
    private QuizModel model;

    public void inicializarController(Jogador jogador, EstatisticasView view) {
        this.jogador = jogador;
        this.view = view;
        model.attachObserver(this);
        atualizarEstatisticas();
    }

    /**
     * Atualiza as estatísticas exibidas no painel.
     */
    private void atualizarEstatisticas() {
        atualizarLabelComValor(lblMaiorSequenciaAcertos, EstatisticaJogador.getMaiorSequenciaAcerto());
        atualizarLabelComValor(lblAssuntoMaiorAcerto, EstatisticaJogador.getAssuntoMaiorAcerto());
        atualizarLabelComValor(lblAssuntoMenorAcerto, EstatisticaJogador.getAssuntoMenorAcerto());
        atualizarLabelComValor(lblMaiorPontuacao, EstatisticaJogador.getMaiorPontuacao());
        atualizarLabelComValor(lblTotalAcertos, EstatisticaJogador.getTotalAcertos());
        atualizarLabelComValor(lblTotalErros, EstatisticaJogador.getTotalErros());
    }

    /**
     * Atualiza o conteúdo de uma Label com o valor fornecido.
     *
     * @param label Label a ser atualizada.
     * @param valor Valor a ser exibido.
     */
    private void atualizarLabelComValor(Label label, Object valor) {
        label.setText(String.valueOf(valor));
    }

    @FXML
    public void voltarMenu() {
        model.detachObserver(this);
        TelaMenuView telaMenu = new TelaMenuView();
        telaMenu.initTelaMenuView((Stage) lblMaiorSequenciaAcertos.getScene().getWindow(), model);
        System.out.println(MENSAGEM_VOLTAR_MENU);
    }

    @Override
    public void update() {
        atualizarEstatisticas();
    }
}