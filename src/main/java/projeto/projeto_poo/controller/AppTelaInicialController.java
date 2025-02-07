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
    private Label lblAviso;

    @FXML
    private Label lblInformacao;

    @FXML
    TextField entradaNomeJogador;

    @FXML
    private Button btnIniciar;

    @FXML
    private void iniciarEntradaJogador() {
        Jogador jogador = new Jogador(entradaNomeJogador.getText().trim());

        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Oi");

    }
}