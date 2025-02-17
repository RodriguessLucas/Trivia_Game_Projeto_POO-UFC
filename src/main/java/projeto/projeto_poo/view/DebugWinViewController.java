package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.swing.interop.SwingInterOpUtils;
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
    private DebugWinView debugWinView;
    private Dificuldade dificuldade;
    private Assunto assunto;
    private Timeline timer;
    private int tempoRestante;

    public void initDebugWinViewController(Configuracoes config){
        this.debugWinJogo = new DebugWin(config);
        debugWinJogo.attachObserver(this);
        carregarQuestao();
    }

    public void initDebugWinViewController(Dificuldade dificuldade, Assunto assunto, Configuracoes config){
        this.debugWinJogo = new DebugWin(config, dificuldade, assunto);
        this.assunto = assunto;
        this.dificuldade = dificuldade;
        debugWinJogo.attachObserver(this);
        carregarQuestao();
    }

    private void carregarQuestao(){
        if(!debugWinJogo.temMaisQuestao()){
            encerrarDebugWin();
            return;
        }

        Questao questaoAtual = debugWinJogo.getQuestaoAtual();
        lblPergunta.setText(questaoAtual.getPergunta());

        btnLetraA.setText(questaoAtual.getAlternativas().get(0));
        btnLetraB.setText(questaoAtual.getAlternativas().get(1));
        btnLetraC.setText(questaoAtual.getAlternativas().get(2));
        btnLetraD.setText(questaoAtual.getAlternativas().get(3));
        // essa aqui é para apresentar a pontuação, não sei se é legal deixar a pontuacao na questao ou so dps de acertar ou errar
        System.out.println(debugWinJogo.getConfiguracoes().getPontuacaoPorDificuldade(questaoAtual.getDificuldade().getDescricao()));
        lblExibirPontuacaoQuestao.setText("pontuacao da questao: " + debugWinJogo.getConfiguracoes().getPontuacaoPorDificuldade(questaoAtual.getDificuldade().getDescricao()));

        iniciarContadorDeTempo();

    }

    //aqui é a chamada ao clicar nos botoes das alternativas
    // Pensei em ser so direto na processarResposta e os botoes chamar a mesma função, mas acho q da problema
    @FXML
    private void responderA(){ processarResposta(1);}
    @FXML
    private void responderB(){ processarResposta(2);}
    @FXML
    private void responderC(){ processarResposta(3);}
    @FXML
    private void responderD(){ processarResposta(4);}

    private void processarResposta(int resposta){
        debugWinJogo.responderQuestao(resposta);
        carregarQuestao();
    }


    private void iniciarContadorDeTempo() {
        if (timer != null) {
            timer.stop();
        }

        if(debugWinJogo.getAssunto() == null && debugWinJogo.getDificuldade() == null ){
            tempoRestante = debugWinJogo.getConfiguracoes().getTempoPorDificuldade("Aleatória");
        }
        else{
            tempoRestante = debugWinJogo.getConfiguracoes().getTempoPorDificuldade(dificuldade.getDescricao());
        }

        lblExibirTempoPorPergunta.setText("Tempo: " + this.tempoRestante + "s");

        // 🔥 Criando um contador que diminui o tempo a cada segundo
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

        System.out.println("⏳ Tempo esgotado! Pulando para a próxima questão.");
        debugWinJogo.responderQuestao(-1);
        carregarQuestao();
    }

    private void encerrarDebugWin(){
        if (timer != null) {
            timer.stop();
        }
        debugWinJogo.encerrarJogo();
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

    }

    @FXML
    private void desistirJogo() {
        if (timer != null) {
            timer.stop();
        }

        System.out.println("🚨 Jogo cancelado! Retornando ao menu principal...");

        TelaMenuView menuView = new TelaMenuView();
        menuView.initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), new Jogador());// tem q arrumar o jogador

    }

    @FXML
    private void finalizarJogo() {

        TelaMenuView menuView = new TelaMenuView();
        menuView.initTelaMenuView((Stage) btnDesistir.getScene().getWindow(), new Jogador()); // tem q arrumar o jogador
        // adicionar metodo para atualizar as estatisticas
    }

    @Override
    public void update() {
        carregarQuestao();
    }
}
