package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;

public class TelaMenuViewController {
    @FXML private Label txtEntradaJogador;
    @FXML private Button btnIniciarJogo;
    @FXML private Button btnEstatisticas;
    @FXML private Button btnConfiguracoes;

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
    public  void iniciarMenuDificuldade() {
        ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/menuDificuldade-view.fxml",600,800);
        System.out.println("Indo para tela de menu dificuldade");
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
