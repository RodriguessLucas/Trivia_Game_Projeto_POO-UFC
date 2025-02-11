package projeto.projeto_poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class GameMenuController {
    @FXML
    private Label txtEntradaJogador;

    @FXML
    private Button btnIniciarJogo;

    @FXML
    private Button btnEstatisticas;

    @FXML
    private Button btnConfiguracoes;



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
        ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/application-view.fxml",600,800);
        System.out.println("Indo para tela Inicial");
    }







}
