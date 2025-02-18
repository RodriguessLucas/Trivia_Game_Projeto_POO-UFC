package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import projeto.projeto_poo.model.*;

public class DebugWinViewController implements Observer {
    @FXML private Label lblExibirTempoPorPergunta;
    @FXML private Label lblExibirPontuacaoQuestao;
    @FXML private Label lblPergunta;
    @FXML private Button btnLetraA;
    @FXML private Button btnLetraB;
    @FXML private Button btnLetraC;
    @FXML private Button btnLetraD;
    @FXML private Button btnPularQuestao;
    @FXML private Button btnDesistir;
    @FXML private Button btnFinalizarJogo;

    private Model model;
    private DebugWinView view;
    private Timeline timer;
    private int tempoRestante;
    private int qntdPulos = 2;

    public void initDebugWinViewController(Model model, DebugWinView view) {
        this.model = model;
        this.view = view;
        model.adicionarObservador(this);
        atualizarQuestao();
    }

    @Override
    public void update() {
        atualizarQuestao();
    }

    public void atualizarQuestao() {
        Questao questaoAtual = model.getQuestaoAtual();
        if (questaoAtual == null) {
            encerrarDebugWin();
            return;
        }

        lblPergunta.setText(questaoAtual.getPergunta());
        btnLetraA.setText(questaoAtual.getAlternativas().get(0));
        btnLetraB.setText(questaoAtual.getAlternativas().get(1));
        btnLetraC.setText(questaoAtual.getAlternativas().get(2));
        btnLetraD.setText(questaoAtual.getAlternativas().get(3));
        lblExibirPontuacaoQuestao.setText("Pontuação: " + model.getConfiguracoes().getPontuacaoPorDificuldade(questaoAtual.getDificuldade().getDescricao()));

        iniciarContadorDeTempo();
    }

    @FXML
    private void responderA() { processarResposta(0); }
    @FXML
    private void responderB() { processarResposta(1); }
    @FXML
    private void responderC() { processarResposta(2); }
    @FXML
    private void responderD() { processarResposta(3); }

    private void processarResposta(int resposta) {
        model.responderQuestao(resposta);
        atualizarQuestao();
    }

    private void iniciarContadorDeTempo() {
        if (timer != null) timer.stop();

        tempoRestante = model.getConfiguracoes().getTempoPorDificuldade("Aleatória");
        lblExibirTempoPorPergunta.setText("Tempo: " + tempoRestante + "s");

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tempoRestante--;
            lblExibirTempoPorPergunta.setText("Tempo: " + tempoRestante + "s");

            if (tempoRestante <= 0) {
                timer.stop();
                pularQuestao();
            }
        }));

        timer.setCycleCount(tempoRestante);
        timer.play();
    }

    @FXML
    private void pularQuestao() {
        if (timer != null) timer.stop();
        if (qntdPulos > 0) {
            model.responderQuestao(-1);
            atualizarQuestao();
            qntdPulos--;
        } else {
            btnPularQuestao.setDisable(true);
        }
    }

    private void encerrarDebugWin() {
        if (timer != null) timer.stop();

        lblPergunta.setText("Fim do jogo!");
        lblExibirTempoPorPergunta.setText("");
        lblExibirPontuacaoQuestao.setText("Pontuação Final: " + model.getPontuacao());

        btnLetraA.setDisable(true);
        btnLetraB.setDisable(true);
        btnLetraC.setDisable(true);
        btnLetraD.setDisable(true);
        btnDesistir.setDisable(true);
        btnPularQuestao.setDisable(true);

        btnFinalizarJogo.setDisable(false);
        btnFinalizarJogo.setVisible(true);
    }

    @FXML
    private void desistirJogo() {
        Stage stageAtual = (Stage) btnDesistir.getScene().getWindow();
        stageAtual.close();
        view.removerObservador();

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(new Stage());
    }

    @FXML
    private void finalizarJogo() {
        model.atualizarEstatistica();
        Stage stageAtual = (Stage) btnFinalizarJogo.getScene().getWindow();
        stageAtual.close();
        view.removerObservador();

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(new Stage());
    }
}
