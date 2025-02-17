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

    @FXML
    private Label lblExibirTempoPorPergunta;

    @FXML
    private Label lblExibirPontuacaoQuestao;

    @FXML
    private Label lblPergunta;

    @FXML
    private Button btnLetraA;

    @FXML
    private Button btnLetraB;

    @FXML
    private Button btnLetraC;

    @FXML
    private Button btnLetraD;

    @FXML
    private Button btnPularQuestao;

    @FXML
    private Button btnDesistir;

    @FXML
    private Button btnFinalizarJogo;

    private DebugWin debugWinJogo;
    private Dificuldade dificuldade;
    private Assunto assunto;
    private Timeline timer;
    private int tempoRestante;
    private int qntdPulos = 2;
    private boolean observadorRegistrado = false; // ✅ Variável para evitar múltiplos observadores

    public void initDebugWinViewController(Configuracoes config){
        this.debugWinJogo = new DebugWin(config);
        lblPergunta.setWrapText(true);
        btnLetraA.setWrapText(true);btnLetraB.setWrapText(true);btnLetraC.setWrapText(true);btnLetraD.setWrapText(true);
        adicionarObserver();
        carregarQuestao();
    }

    public void initDebugWinViewController(Dificuldade dificuldade, Assunto assunto, Configuracoes config, int qntdQuestoes){
        this.debugWinJogo = new DebugWin(config, dificuldade, assunto, qntdQuestoes);
        this.assunto = assunto;
        this.dificuldade = dificuldade;
        adicionarObserver();
        carregarQuestao();
    }

    private void adicionarObserver() {
        if (!observadorRegistrado) {
            debugWinJogo.attachObserver(this);
            observadorRegistrado = true;
        }
    }

    private void carregarQuestao(){
        if(!debugWinJogo.temMaisQuestao()){
            encerrarDebugWin();
            return;
        }

        Questao questaoAtual = debugWinJogo.getQuestaoAtual();
        System.out.println("Resposta: " + questaoAtual.getCorreta());
        lblPergunta.setText(questaoAtual.getPergunta());
        ajustarFonteLabel(lblPergunta, questaoAtual.getPergunta());


        btnLetraA.setText(questaoAtual.getAlternativas().get(0));
        btnLetraB.setText(questaoAtual.getAlternativas().get(1));
        btnLetraC.setText(questaoAtual.getAlternativas().get(2));
        btnLetraD.setText(questaoAtual.getAlternativas().get(3));


        ajustarFonteBotao(btnLetraA);
        ajustarFonteBotao(btnLetraB);
        ajustarFonteBotao(btnLetraC);
        ajustarFonteBotao(btnLetraD);


        lblExibirPontuacaoQuestao.setText("Pontuação da questão: " + debugWinJogo.getConfiguracoes().getPontuacaoPorDificuldade(questaoAtual.getDificuldade().getDescricao()));

        iniciarContadorDeTempo();
    }

    @FXML
    private void responderA(){ processarResposta(1); }
    @FXML
    private void responderB(){ processarResposta(2); }
    @FXML
    private void responderC(){ processarResposta(3); }
    @FXML
    private void responderD(){ processarResposta(4); }

    private void processarResposta(int resposta){
        debugWinJogo.responderQuestao(resposta);
        carregarQuestao();
    }

    private void iniciarContadorDeTempo() {
        if (timer != null) {
            timer.stop();
        }

        tempoRestante = (debugWinJogo.getAssunto() == null && debugWinJogo.getDificuldade() == null)
                ? debugWinJogo.getConfiguracoes().getTempoPorDificuldade("Aleatória")
                : debugWinJogo.getConfiguracoes().getTempoPorDificuldade(dificuldade.getDescricao());

        lblExibirTempoPorPergunta.setText("Tempo: " + this.tempoRestante + "s");

        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            this.tempoRestante--;
            lblExibirTempoPorPergunta.setText("Tempo: " + this.tempoRestante + "s");

            if (this.tempoRestante <= 0) {
                timer.stop();
                pularQuestao();
            }
        }));

        timer.setCycleCount(this.tempoRestante);
        timer.play();
    }


    @FXML
    private void pularQuestao() {
        if (timer != null) {
            timer.stop();
        }

        if(qntdPulos >0){
            debugWinJogo.responderQuestao(-1);
            carregarQuestao();
            qntdPulos--;
        }
        else{
            btnPularQuestao.setDisable(true);
        }

    }

    private void encerrarDebugWin(){
        if (timer != null) {
            timer.stop();
        }


        lblPergunta.setText("Questões Finalizadas!");
        lblPergunta.setText("Sua pontuação total: "+ debugWinJogo.getPontuacao());
        lblExibirTempoPorPergunta.setText("");
        lblExibirPontuacaoQuestao.setText("");

        btnLetraA.setDisable(true);
        btnLetraB.setDisable(true);
        btnLetraC.setDisable(true);
        btnLetraD.setDisable(true);
        btnDesistir.setDisable(true);
        btnPularQuestao.setDisable(true);

        btnFinalizarJogo.setDisable(false);
        btnFinalizarJogo.setVisible(true);
        debugWinJogo.detachObserver(this);

    }

    @FXML
    private void desistirJogo() {
        if (timer != null) {
            timer.stop();
        }
        debugWinJogo.detachObserver(this);
        TelaMenuView menuView = new TelaMenuView();
        menuView.initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), debugWinJogo.getConfiguracoes().getJogador());
    }

    @FXML
    private void finalizarJogo() {


        if(debugWinJogo.getPontuacao() > EstatisticaJogador.getMaiorPontuacao()){
            EstatisticaJogador.setMaiorPontuacao(debugWinJogo.getPontuacao());
        }
        EstatisticaJogador.setMaiorSequenciaAcerto(debugWinJogo.getMaiorSequenciaAcerto());
        EstatisticaJogador.contabilizarAcertosAssunto(debugWinJogo.getAuxAcertosPorAssunto());
        EstatisticaJogador.contabilizarErrosAssunto(debugWinJogo.getAuxErrosPorAssunto());
        EstatisticaJogador.atualizarEstatisticaJogador();

        debugWinJogo.detachObserver(this);
        TelaMenuView menuView = new TelaMenuView();
        menuView.initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), debugWinJogo.getConfiguracoes().getJogador());// arruma esse new jogador
    }

    @Override
    public void update() {
        carregarQuestao();

    }

    private void ajustarFonteBotao(Button botao) {
        double tamanhoFonte = 16;
        double larguraBotao = botao.getWidth() - 10;
        Text textNode = new Text(botao.getText());
        textNode.setFont(Font.font(tamanhoFonte));
        textNode.setBoundsType(TextBoundsType.VISUAL);

        while (textNode.getLayoutBounds().getWidth() > larguraBotao && tamanhoFonte > 8) {
            tamanhoFonte -= 1;
            textNode.setFont(Font.font(tamanhoFonte));
        }
        botao.setFont(Font.font(tamanhoFonte));
    }


    private void ajustarFonteLabel(Label label, String texto) {
        double tamanhoFonte = 16;
        double larguraMaxima = 600;

        Text textNode = new Text(texto);
        textNode.setFont(Font.font(tamanhoFonte));
        textNode.setBoundsType(TextBoundsType.VISUAL);

        while (textNode.getLayoutBounds().getWidth() > larguraMaxima && tamanhoFonte > 10) {
            tamanhoFonte -= 2;
            textNode.setFont(Font.font(tamanhoFonte));
        }
        label.setFont(Font.font(tamanhoFonte));
        label.setText(texto);
    }




}
