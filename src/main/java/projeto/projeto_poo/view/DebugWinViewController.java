package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Questao questaoAtual = model.getDebugWinQuestaoAtual();
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
    private void responderA() {
        processarResposta(1, btnLetraA);
    }

    @FXML
    private void responderB() {
        processarResposta(2, btnLetraB);
    }

    @FXML
    private void responderC() {
        processarResposta(3, btnLetraC);
    }

    @FXML
    private void responderD() {
        processarResposta(4, btnLetraD);
    }

    private void processarResposta(int resposta, Button btnResposta) {
        // Para o contador de tempo imediatamente
        if (timer != null) {
            timer.stop();
        }

        boolean acertou = model.debugWinResponderQuestao(resposta);

        // Muda a cor do botão para verde (acertou) ou vermelho (errou)
        btnResposta.setStyle(acertou ? "-fx-background-color: #7df37d;" : "-fx-background-color: #f17474;");


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {

            btnResposta.setStyle("");

            if (model.debugWinTemMaisQuestao()) {
                atualizarQuestao();
            } else {
                encerrarDebugWin();
            }
        }));

        timeline.setCycleCount(1);
        timeline.play();
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
            if (tempoRestante == 0 && qntdPulos == 0) {
                mostrarAlerta("Encerrando jogo por inatividade...", Alert.AlertType.INFORMATION);
                desistirJogo();
            }
        }));

        timer.setCycleCount(tempoRestante);
        timer.play();
    }

    @FXML
    private void pularQuestao() {
        if (timer != null) timer.stop();
        if (qntdPulos > 0) {
            model.debugWinResponderQuestao(-1);
            atualizarQuestao();
            qntdPulos--;
            if (qntdPulos == 0) {
                btnPularQuestao.setDisable(true);
            }
        }
    }

    private void encerrarDebugWin() {
        if (timer != null) timer.stop();

        lblPergunta.setText("Fim do jogo!");
        lblExibirTempoPorPergunta.setText("");
        lblExibirPontuacaoQuestao.setText("Pontuação Final: " + model.getDebugWinPontuacao());

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
        model.encerrarJogo();
        Stage stageAtual = (Stage) btnDesistir.getScene().getWindow();
        stageAtual.close();
        view.removerObservador();

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(new Stage());
    }

    @FXML
    private void finalizarJogo() {
        model.finalizarDebugWin();
        Stage stageAtual = (Stage) btnFinalizarJogo.getScene().getWindow();
        stageAtual.close();
        view.removerObservador();

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(new Stage());
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
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
