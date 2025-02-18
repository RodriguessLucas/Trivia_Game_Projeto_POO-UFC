package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    public void iniciarJogoAleatorio() {}
    @FXML
    public void iniciarPythonFacilCurto() {}
    @FXML
    public void iniciarPOOFacil() {}
    @FXML
    public void iniciarEDFacil() {}
    @FXML
    public void iniciarJavaFacil() {}
    @FXML
    public void iniciarJavaDificil() {}
    @FXML
    public void iniciarEDDificil() {}
    @FXML
    public void iniciarCMedio() {}
    @FXML
    public void estatisticas() {}
    @FXML
    public void configuracoes() {}

    public void irTelaInicial() {
        Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow(); // Obtém a referência da janela atual
        stageAtual.close(); // Fecha a tela do menu

        TelaInicialView telaInicial = new TelaInicialView(model);
        telaInicial.initTelaInicial(new Stage()); // Abre a tela inicial novamente

        model.removerObservador(this);
        System.out.println("Voltando para a tela inicial...");
    }

    public void update() {}
    @FXML
    public void teste() {}
}


/*
    public void initTelaMenuViewController(Model model, TelaMenuView view) {
        this.model = model;
        this.view = view;
        setMensagem(model.getJogador().getNome());
        model.adicionarObservador(this);
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
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(), Configuracoes.getInstancia(jogador));
    }

    @FXML
    public void iniciarPythonFacilCurto(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.PYTHON, 5);
    }

    @FXML
    public void iniciarPOOFacil(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
       // telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.POO, 5);
    }


    @FXML
    public void iniciarEDFacil(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
       // telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.ESTRUTURADADOS, 5);
    }

    @FXML
    public void iniciarJavaFacil(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.FACIL, Assunto.JAVA, 5);
    }

    @FXML
    public void iniciarJavaDificil(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.DIFICIL, Assunto.JAVA, 8);
    }

    @FXML
    public void iniciarEDDificil(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.DIFICIL, Assunto.ESTRUTURADADOS, 8);
    }

    @FXML
    public void iniciarCMedio(){
        //DebugWinView telaDeJogoAleatorio = new DebugWinView();
        //telaDeJogoAleatorio.initDebugWinView((Stage) btnJogoAleatorio.getScene().getWindow(),Configuracoes.getInstancia(jogador), Dificuldade.MEDIO, Assunto.C, 7);
    }



    @FXML
    public void estatisticas() {
        //EstatisticasView telaEstatisticas = new EstatisticasView();
       // telaEstatisticas.initEstatisticasView((Stage) btnEstatisticas.getScene().getWindow(), jogador);
        //System.out.println("Indo para a tela de estatísticas...");
    }

    @FXML
    public void configuracoes() {
        //Stage stageAtual = (Stage) btnConfiguracoes.getScene().getWindow(); // Obtém o Stage correto
       // ConfiguracaoView telaConfiguracoes = new ConfiguracaoView();
        //telaConfiguracoes.initConfiguracaoView(stageAtual, jogador);
        //System.out.println("Indo para a tela de configurações...");
    }


    public void irTelaInicial() {
        //TelaInicialView telaInicial = new TelaInicialView();
        //telaInicial.initTelaInicial((Stage) btnConfiguracoes.getScene().getWindow(), model.getJogador(), model);
       // model.removerObservador(this);

        //System.out.println("Voltando para a tela inicial...");
    }

    public void update() {}

    @FXML
    public void teste(){}



}


 */