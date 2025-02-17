package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;

public class TelaInicialViewController {
    @FXML private Label txtInstrucao;
    @FXML private Label lblInformacao;
    @FXML private TextField entradaNomeJogador;
    @FXML private Button btnIniciar;

    private Jogador jogador;
    private TelaInicialView view;


    public void initTelaInicialViewController(Jogador jogador, TelaInicialView view) {
        this.jogador = jogador;
        this.view = view;
        System.out.println("entradaNomeJogador: " + entradaNomeJogador);
    }

    @FXML
    private void handleEvent() {
        if (jogador == null) {
            System.out.println("Erro: jogador não foi inicializado!");
            return;
        }

        String nome = entradaNomeJogador.getText().trim();
        if (nome.isEmpty() || (nome.equalsIgnoreCase("Ex: astuto")) || (nome.matches("\\s*")) ) {
            jogador.setNome("Jogador");
        }
        else{
            jogador.setNome(nome);
        }

        System.out.println("Nome salvo no jogador: " + jogador.getNome());


        TelaMenuView telaMenu = new TelaMenuView();
        telaMenu.initTelaMenuView((Stage) btnIniciar.getScene().getWindow(), jogador);
    }
}
