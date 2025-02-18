package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.*;

public class TelaMenuViewController {

    @FXML
    private Label txtEntradaJogador;

    @FXML
    private Button btnIniciarJogo;
    @FXML
    private Button btnEstatisticas;
    @FXML
    private Button btnConfiguracoes;
    @FXML
    private Button btnJogoAleatorio;
    @FXML
    private Button btnPythonFacilCurto;
    @FXML
    private Button btnPOOFacil;
    @FXML
    private Button btnEDFacil;
    @FXML
    private Button btnJavaFacil;
    @FXML
    private Button btnJavaDificil;
    @FXML
    private Button btnEDDificil;
    @FXML
    private Button btnCMedio;

    private static final int FACIL_QUESTOES = 5;
    private static final int MEDIO_QUESTOES = 7;
    private static final int DIFICIL_QUESTOES = 8;

    private QuizModel model;
    private TelaMenuView view;

    public void initTelaMenuViewController(QuizModel model, TelaMenuView view) {
        this.model = model;
        this.view = view;
        setMensagem(model.getJogador().getNome());
    }

    @FXML
    public void setMensagem(String mensagem) {
        if (mensagem == null) {
            System.out.println("O texto está vazio");
            return;
        }
        txtEntradaJogador.setText("Bem-vindo, " + mensagem + "!");
    }

    @FXML
    public void iniciarJogoAleatorio() {
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView(
                (Stage) btnJogoAleatorio.getScene().getWindow(),
                Configuracoes.getInstancia(jogador)
        );
    }

    @FXML
    private void iniciarPythonFacilCurto() {
        iniciarJogo(Dificuldade.FACIL, Assunto.PYTHON, FACIL_QUESTOES);
    }

    @FXML
    private void iniciarPOOFacil() {
        iniciarJogo(Dificuldade.FACIL, Assunto.POO, FACIL_QUESTOES);
    }

    @FXML
    private void iniciarEDFacil() {
        iniciarJogo(Dificuldade.FACIL, Assunto.ESTRUTURADADOS, FACIL_QUESTOES);
    }

    @FXML
    private void iniciarJavaFacil() {
        iniciarJogo(Dificuldade.FACIL, Assunto.JAVA, FACIL_QUESTOES);
    }

    @FXML
    private void iniciarJavaDificil() {
        iniciarJogo(Dificuldade.DIFICIL, Assunto.JAVA, DIFICIL_QUESTOES);
    }

    @FXML
    private void iniciarEDDificil() {
        iniciarJogo(Dificuldade.DIFICIL, Assunto.ESTRUTURADADOS, DIFICIL_QUESTOES);
    }

    @FXML
    private void iniciarCMedio() {
        iniciarJogo(Dificuldade.MEDIO, Assunto.C, MEDIO_QUESTOES);
    }


    private void iniciarJogo(Dificuldade dificuldade, Assunto assunto, int questoes) {
        DebugWinView telaDeJogo = new DebugWinView();
        telaDeJogo.initialize((Stage) btnJogoAleatorio.getScene().getWindow(), model, dificuldade, assunto, questoes);

    }

    @FXML
    public void estatisticas() {
        EstatisticasView telaEstatisticas = new EstatisticasView();
        telaEstatisticas.initEstatisticasView((Stage) btnEstatisticas.getScene().getWindow(), jogador);
        System.out.println("Indo para a tela de estatísticas...");
    }

    @FXML
    public void configuracoes() {
        Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow();
        ConfiguracaoView telaConfiguracoes = new ConfiguracaoView();
        telaConfiguracoes.initConfiguracaoView(stageAtual, jogador);
        System.out.println("Indo para a tela de configurações...");
    }

    public void irTelaInicial() {
        TelaInicialView telaInicial = new TelaInicialView();
        telaInicial.handleEvent((Stage) btnConfiguracoes.getScene().getWindow(), jogador);
        System.out.println("Voltando para a tela inicial...");
    }

    @FXML
    public void teste() {
    }
}