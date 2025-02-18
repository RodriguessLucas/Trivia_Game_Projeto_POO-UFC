package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Model;

public class TelaMenuViewController implements Observer {
    @FXML private Label txtEntradaJogador;
    @FXML private Button btnIniciarJogo;
    @FXML private Button btnEstatisticas;
    @FXML private Button btnConfiguracoes;
    @FXML private Button btnJogoAleatorio;
    @FXML private Button btnPythonFacilCurto;
    @FXML private Button btnPOOFacil;
    @FXML private Button btnEDFacil;
    @FXML private Button btnJavaFacil;
    @FXML private Button btnJavaDificil;
    @FXML private Button btnEDDificil;
    @FXML private Button btnCMedio;

    private Model model;
    private TelaMenuView view;

    public void initTelaMenuViewController(Model model, TelaMenuView view) {
        this.model = model;
        this.view = view;
        setMensagem(model.getJogador().getNome());
        model.adicionarObservador(this);
    }

    @FXML
    public void setMensagem(String mensagem) {
        if (mensagem == null) {
            System.out.println("O texto está vazio");
            return;
        }
        txtEntradaJogador.setText("Bem-vindo, " + mensagem + "!");
    }

    // Métodos comentados mantidos
    @FXML
    public void iniciarJogoAleatorio() {
        Stage stageAtual = (Stage) btnJogoAleatorio.getScene().getWindow();
        stageAtual.close();

        model.iniciarDebugWinAleatorio();

        DebugWinView debugWinView = new DebugWinView(model);
        debugWinView.initDebugWinView(new Stage());
    }

    @FXML
    public void iniciarPythonFacilCurto() {
        Stage stageAtual = (Stage) btnJogoAleatorio.getScene().getWindow();
        stageAtual.close();

        model.iniciarJogoPersonalizado(Dificuldade.FACIL, Assunto.PYTHON, 5);

        DebugWinView debugWinView = new DebugWinView(model);
        debugWinView.initDebugWinView(new Stage());

    }

    @FXML
    public void iniciarPOOFacil() {
        carregarTelaJogos(Dificuldade.FACIL, Assunto.POO, 5);
    }

    @FXML
    public void iniciarEDFacil() {
        carregarTelaJogos(Dificuldade.FACIL, Assunto.ESTRUTURADADOS, 5);
    }

    @FXML
    public void iniciarJavaFacil() {
        carregarTelaJogos(Dificuldade.FACIL, Assunto.JAVA, 5);
    }

    @FXML
    public void iniciarJavaDificil() {
        carregarTelaJogos(Dificuldade.DIFICIL, Assunto.JAVA, 8);
    }

    @FXML
    public void iniciarEDDificil() {
        carregarTelaJogos(Dificuldade.DIFICIL, Assunto.ESTRUTURADADOS, 8);
    }

    @FXML
    public void iniciarCMedio() {
        carregarTelaJogos(Dificuldade.MEDIO, Assunto.C, 7);
    }


    @FXML
    public void estatisticas() {
        Stage stageAtual = (Stage) btnEstatisticas.getScene().getWindow();
        stageAtual.close();

        EstatisticasView estatisticasView = new EstatisticasView(model);
        estatisticasView.initEstatisticasView(new Stage());

        System.out.println("Indo para a tela de estatísticas...");
    }

    @FXML
    public void configuracoes() {
        Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow();
        stageAtual.close();
        ConfiguracaoView configuracaoView = new ConfiguracaoView(model);
        configuracaoView.initConfiguracaoView(new Stage());
        stageAtual.close();

        System.out.println("Indo para a tela de configuracoes...");
    }

    public void irTelaInicial() {
        Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow();
        stageAtual.close();

        TelaInicialView telaInicial = new TelaInicialView(model);
        telaInicial.initTelaInicial(new Stage());

        model.removerObservador(this);
        System.out.println("Voltando para a tela inicial...");
    }

    private void carregarTelaJogos(Dificuldade dificuldade, Assunto assunto, int qntd) {
        Stage stageAtual = (Stage) btnJogoAleatorio.getScene().getWindow();
        stageAtual.close();

        model.iniciarJogoPersonalizado(dificuldade, assunto, qntd);

        DebugWinView debugWinView = new DebugWinView(model);
        debugWinView.initDebugWinView(new Stage());

    }


    public void update() {}
    @FXML
    public void teste() {}
}

