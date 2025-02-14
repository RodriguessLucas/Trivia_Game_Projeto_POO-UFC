package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/estatisticas-view.fxml",600,800);
        System.out.println("Indo para tela estatisticas");
    }

    @FXML
    public void configuracoes() {
        ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/config-view.fxml",600,800);
        System.out.println("Indo para tela configuracoes");
    }

    @FXML
    public void irTelaInicial() {
        ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/telaInicial-view.fxml",600,800);
        System.out.println("Indo para tela Inicial");
    }







}
