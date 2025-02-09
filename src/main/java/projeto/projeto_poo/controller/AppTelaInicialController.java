package projeto.projeto_poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import projeto.projeto_poo.model.Jogador;

public class AppTelaInicialController {
    @FXML
    private Label txtInstrucao;

    @FXML
    private Label lblInformacao;

    @FXML
    TextField entradaNomeJogador;

    @FXML
    private Button btnIniciar;

    @FXML
    public void initialize() {
        if (btnIniciar == null) {
            System.out.println("ERRO: btnIniciar está NULL!");
        } else {
            System.out.println("btnIniciar carregado corretamente!");
        }
    }

    @FXML
    private void iniciarEntradaJogador() {
        Jogador jogador = new Jogador(entradaNomeJogador.getText().trim());

        try{
            GameMenuController gameMenuController = ScreenManager.alterarTelaComController("/projeto/projeto_poo/view/gameMenu-view.fxml",600,800);
            gameMenuController.setMensagem(jogador.getNome());

        }catch(NullPointerException e){
            System.out.println("Erro ao carregar a tela");
        }
    }
}