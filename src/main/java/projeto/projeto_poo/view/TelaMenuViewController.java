package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Configuracoes;
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
        System.out.println("Iniciando Debug & Win aleatorio");
        // aqui é do botao iniciar jogo aleatorio
    }


    @FXML
    public void iniciarJogoPersonalizado(){
        // aqui chamará nos quizz predefinidos
        System.out.println("Iniciando Debug & Win  predefinido!");

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
