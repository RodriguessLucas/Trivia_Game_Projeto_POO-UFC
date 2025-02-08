package projeto.projeto_poo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

public class GameController {
    @FXML
    public Label txtEntradaJogador;

    @FXML
    public void setMensagem(String mesagem) {
        if (mesagem == null) {
            System.out.println("O texto está vazio");
            return;
        }
        txtEntradaJogador.setText("Bem-Vindo "+ mesagem);
    }


}
