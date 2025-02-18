package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Duration;
import projeto.projeto_poo.model.*;

public class DebugWinViewController implements Observer {
    private static final int FONT_SIZE_DEFAULT = 16;
    private static final int FONT_SIZE_MIN = 8;
    private static final int MAX_PULOS = 2;

    @FXML
    private Label lblTempoPorPergunta, lblPontuacaoQuestao, lblPergunta;
    @FXML
    private Button btnLetraA, btnLetraB, btnLetraC, btnLetraD, btnPular, btnDesistir, btnFinalizar;

    private QuizModel model;
    private DebugWin debugWin;
    private Dificuldade dificuldade;
    private Assunto assunto;
    private int totalQuestoes;
    private Timeline timer;
    private int tempoRestante, qntdPulos = MAX_PULOS;
    private boolean isObserverAttached = false;

    public void initializeController(Dificuldade dificuldade, Assunto assunto, int totalQuestoes ,QuizModel model) {
        this.dificuldade = dificuldade;
        this.assunto = assunto;
        this.model = model;
        this.totalQuestoes = totalQuestoes;
        initJogo(new DebugWin());
        //(config, dificuldade, assunto, totalQuestoes));
        configurarTela();
    }

    private void initJogo(DebugWin debugWin) {
        this.debugWin = debugWin;
        model.iniciarQuizPersonalizado(dificuldade, assunto, totalQuestoes, debugWin);
        adicionarObserver();
    }

    private void configurarTela() {
        lblPergunta.setWrapText(true);
        configurarBotoesDeAlternativa(btnLetraA, btnLetraB, btnLetraC, btnLetraD);
        carregarQuestao();
    }

    private void configurarBotoesDeAlternativa(Button... botoes) {
        for (Button botao : botoes) {
            botao.setWrapText(true);
            ajustarFonteBotao(botao);
        }
    }

    private void adicionarObserver() {
        if (!isObserverAttached) {
            this.attachObserver(this);
            isObserverAttached = true;
        }
    }

    private void attachObserver(DebugWinViewController debugWinViewController) {
        model.attachObserver(debugWinViewController);
    }

    private void carregarQuestao() {
        if (!debugWin.temMaisQuestao()) {
            finalizarJogoComMensagem("Questões Finalizadas!", "Sua pontuação total: " + debugWin.getPontuacao());
            return;
        }

        Questao questaoAtual = debugWin.getQuestaoAtual();
        atualizarInterfaceComQuestao(questaoAtual);
        iniciarContadorDeTempo();
    }

    private void atualizarInterfaceComQuestao(Questao questao) {
        lblPergunta.setText(questao.getPergunta());
        ajustarFonteLabel(lblPergunta, questao.getPergunta());

        String[] alternativas = questao.getAlternativas().toArray(new String[0]);
        btnLetraA.setText(alternativas[0]);
        btnLetraB.setText(alternativas[1]);
        btnLetraC.setText(alternativas[2]);
        btnLetraD.setText(alternativas[3]);

        lblPontuacaoQuestao.setText("Pontuação: " + model.getConfiguracoes().getPontuacaoPorDificuldade(questao.getDificuldade().getDescricao()));
    }

    private void iniciarContadorDeTempo() {
        if (timer != null) timer.stop();

        tempoRestante = (debugWin.getQuestaoAtual() == null)
                ? model.getConfiguracoes().getTempoPorDificuldade("Aleatória")
                : model.getConfiguracoes().getTempoPorDificuldade(dificuldade.getDescricao());

        atualizarTempoLabel();

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tempoRestante--;
            atualizarTempoLabel();
            if (tempoRestante <= 0) {
                timer.stop();
                pularQuestao();
            }
        }));
        timer.setCycleCount(tempoRestante);
        timer.play();
    }

    private void atualizarTempoLabel() {
        lblTempoPorPergunta.setText("Tempo: " + tempoRestante + "s");
    }

    @FXML
    private void responderA() {
        processarResposta(1);
    }

    @FXML
    private void responderB() {
        processarResposta(2);
    }

    @FXML
    private void responderC() {
        processarResposta(3);
    }

    @FXML
    private void responderD() {
        processarResposta(4);
    }

    private void processarResposta(int resposta) {
        debugWin.responderQuestao(resposta, tempoRestante);
        carregarQuestao();
    }

    @FXML
    private void pularQuestao() {
        if (timer != null) timer.stop();

        if (qntdPulos > 0) {
            debugWin.responderQuestao(-1, 0);
            carregarQuestao();
            qntdPulos--;
        } else {
            btnPular.setDisable(true);
        }
    }

    private void finalizarJogoComMensagem(String mensagemLabel, String mensagemPontuacao) {
        if (timer != null) timer.stop();

        lblPergunta.setText(mensagemLabel);
        lblPontuacaoQuestao.setText(mensagemPontuacao);
        lblTempoPorPergunta.setText("");
        ajustarEstadoBotoesFinalizacao();
        ;
    }

    private void ajustarEstadoBotoesFinalizacao() {
        for (Button botao : new Button[]{btnLetraA, btnLetraB, btnLetraC, btnLetraD, btnDesistir, btnPular}) {
            botao.setDisable(true);
        }
        btnFinalizar.setDisable(false);
        btnFinalizar.setVisible(true);
    }

    @FXML
    private void desistirJogo() {
        terminarTimerEDetachObserver();
        new TelaMenuView().initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), model); // !!!!!!!!!!!!!!!!!
    }

    @FXML
    private void finalizarJogo() {
        EstatisticaJogador.atualizarEstatisticaJogador();
        terminarTimerEDetachObserver();
        new TelaMenuView().initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), model); // !!!!!!!!!!!!!!!!!!!!!!!!
    }

    private void terminarTimerEDetachObserver() {
        if (timer != null) timer.stop();
        model.detachObserver(this);
    }

    @Override
    public void update() {
        carregarQuestao();
    }

    private void ajustarFonteBotao(Button botao) {
        ajustarFonte(botao, botao.getText());
    }

    private void ajustarFonteLabel(Label label, String texto) {
        ajustarFonte(label, texto);
    }

    private void ajustarFonte(javafx.scene.Node componente, String texto) {
        double tamanhoFonte = FONT_SIZE_DEFAULT;
        double largura = componente instanceof Button ? ((Button) componente).getWidth() - 10 : ((Label) componente).getWidth() - 10;
        Text textNode = new Text(texto);
        textNode.setFont(Font.font(tamanhoFonte));
        textNode.setBoundsType(TextBoundsType.VISUAL);

        while (textNode.getLayoutBounds().getWidth() > largura && tamanhoFonte > FONT_SIZE_MIN) {
            tamanhoFonte--;
            textNode.setFont(Font.font(tamanhoFonte));
        }

        if (componente instanceof Button) {
            ((Button) componente).setFont(Font.font(tamanhoFonte));
        } else if (componente instanceof Label) {
            ((Label) componente).setFont(Font.font(tamanhoFonte));
        }
    }
}