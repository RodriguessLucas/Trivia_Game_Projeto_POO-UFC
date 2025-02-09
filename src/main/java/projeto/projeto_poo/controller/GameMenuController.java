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
        txtEntradaJogador.setText("Bem-Vindo "+ mesagem);
    }

    @FXML
    public  void iniciarMenuDificuldade() {
        MenuDificuldadeController menuDificuldade = ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/menuDificuldade-view.fxml",600,800);

    }

    @FXML
    public void estatisticas() {
        EstatisticasController estatisticasController = ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/estatisticas-view.fxml",600,800);


    }

    @FXML
    public void configuracoes() {
        ConfigController configController = ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/config-view.fxml",600,800);

    }

    @FXML
    public void irTelaInicial() {
        AppTelaInicialController telaInicial = ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/application-view.fxml",600,800);
        System.out.println("Indo para tela Inicial");
    }







}
