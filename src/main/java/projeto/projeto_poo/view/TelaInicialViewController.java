package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.model.QuizModel;

public class TelaInicialViewController {

    private static final String NOME_PADRAO = "Jogador";

    @FXML
    private Label lblInstrucao;  // Renomeado para padronizar
    @FXML
    private Label lblInformacao;
    @FXML
    private TextField entradaNomeJogador;
    @FXML
    private Button btnIniciar;

    private QuizModel model;
    private TelaInicialView view;

    public void initialize(QuizModel model, TelaInicialView view) { // Renomeação para maior clareza
        this.model = model;
        this.view = view;
        System.out.println("Campo de entrada de nome: " + entradaNomeJogador);
    }

    @FXML
    private void handleEvent() {
        if (!verificarJogadorInicializado()) {
            return;
        }

        model.getJogador().setNome(obterNomeDoJogador());
        System.out.println("Nome salvo no jogador: " + model.getJogador().getNome());

        abrirTelaMenu();
    }

    private boolean verificarJogadorInicializado() {
        if (model.getJogador() == null) {
            System.out.println("Erro: jogador não foi inicializado!");
            return false;
        }
        return true;
    }

    private String obterNomeDoJogador() {
        String nome = entradaNomeJogador.getText().trim();
        if (nome.isEmpty() || nome.equalsIgnoreCase("Ex: astuto") || nome.matches("\\s*")) {
            return NOME_PADRAO;  // Uso da constante
        }
        return nome;
    }

    private void abrirTelaMenu() {
        TelaMenuView telaMenu = new TelaMenuView();
        telaMenu.initTelaMenuView((Stage) btnIniciar.getScene().getWindow(), model);
    }
}