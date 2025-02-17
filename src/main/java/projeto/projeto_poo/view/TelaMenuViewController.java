package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Jogador;

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




    private Jogador jogador;
    private TelaMenuView view;



    public void initTelaMenuViewController(Jogador jogador, TelaMenuView view) {
        this.jogador = jogador;
        this.view = view;
        setMensagem(jogador.getNome());
    }

    @FXML
    public void setMensagem(String mesagem) {
        if (mesagem == null) {
            System.out.println("O texto está vazio");
            return;
        }
        txtEntradaJogador.setText("Bem-vindo, "+ mesagem + "!");
    }


    @FXML
    public  void iniciarJogoAleatorio() {
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(), Configuracoes.getInstancia(jogador));
    }

    @FXML
    public void iniciarPythonFacilCurto(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.PYTHON, 5);
    }

    @FXML
    public void iniciarPOOFacil(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.POO, 5);
    }


    @FXML
    public void iniciarEDFacil(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.ESTRUTURADADOS, 5);
    }

    @FXML
    public void iniciarJavaFacil(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.JAVA, 5);
    }

    @FXML
    public void iniciarJavaDificil(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.DIFICIL, Assunto.JAVA, 8);
    }

    @FXML
    public void iniciarEDDificil(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.DIFICIL, Assunto.ESTRUTURADADOS, 8);
    }

    @FXML
    public void iniciarCMedio(){
        DebugWinView telaDeJogoAleatorio = new DebugWinView();
        telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.MEDIO, Assunto.C, 7);
    }



    @FXML
    public void estatisticas() {
        EstatisticasView telaEstatisticas = new EstatisticasView();
        telaEstatisticas.initEstatisticasView((Stage) btnEstatisticas.getScene().getWindow(), jogador);
        System.out.println("Indo para a tela de estatísticas...");
    }

    @FXML
    public void configuracoes() {
        Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow(); // Obtém o Stage correto
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
    public void teste(){}



}
