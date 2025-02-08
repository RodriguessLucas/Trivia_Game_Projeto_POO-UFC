package projeto.projeto_poo.controller;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;

import java.io.IOException;

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
            initialize();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projeto/projeto_poo/view/game-view.fxml"));

            Parent root = loader.load();


            GameController gameController = loader.getController();
            gameController.setMensagem(jogador.getNome());

            Stage stage = (Stage) btnIniciar.getScene().getWindow();// erro aqui, ao tentar pegar a proxima tela
            stage.setScene(new Scene(root,600,800));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro ao iniciar o view");
        }

    }
}