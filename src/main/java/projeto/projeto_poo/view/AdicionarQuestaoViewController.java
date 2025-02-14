package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.model.Questao;
import projeto.projeto_poo.repository.GerenciadorBanco;

import java.util.Arrays;
import java.util.List;

public class AdicionarQuestaoViewController implements Observer {
    @FXML private TextField txtEnunciado;
    @FXML private ChoiceBox<String> choiceDificuldade;
    @FXML private TextField txtAlternativaA;
    @FXML private TextField txtAlternativaB;
    @FXML private TextField txtAlternativaC;
    @FXML private TextField txtAlternativaD;
    @FXML private ToggleGroup toggleCorreta;
    @FXML private RadioButton radioAlternativaA;
    @FXML private RadioButton radioAlternativaB;
    @FXML private RadioButton radioAlternativaC;
    @FXML private RadioButton radioAlternativaD;
    @FXML private Button btnSalvarQuestao;
    @FXML private Button btnCancelar;

    private Jogador jogador;
    private AdicionarQuestaoView view;

    public void initAdicionarQuestaoViewController(Jogador jogador, AdicionarQuestaoView view) {
        this.jogador = jogador;
        this.view = view;

        choiceDificuldade.getItems().addAll("Fácil", "Médio", "Difícil");
        choiceDificuldade.setValue("Fácil"); // Valor padrão

        Questao.attachObserver(this);
    }

    @FXML
    public void salvarQuestao() {
        String enunciado = txtEnunciado.getText().trim();
        String dificuldade = choiceDificuldade.getValue();
        String alternativaA = txtAlternativaA.getText().trim();
        String alternativaB = txtAlternativaB.getText().trim();
        String alternativaC = txtAlternativaC.getText().trim();
        String alternativaD = txtAlternativaD.getText().trim();

        // Captura qual alternativa foi selecionada como correta
        int correta;
        if (radioAlternativaA.isSelected()) {
            correta = 0;
        } else if (radioAlternativaB.isSelected()) {
            correta = 1;
        } else if (radioAlternativaC.isSelected()) {
            correta = 2;
        } else if (radioAlternativaD.isSelected()) {
            correta = 3;
        } else {
            System.out.println("Erro: Selecione uma alternativa correta!");
            return;
        }

        // Verificação para evitar questões inválidas
        if (enunciado.isEmpty() || alternativaA.isEmpty() || alternativaB.isEmpty() ||
                alternativaC.isEmpty() || alternativaD.isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        List<String> alternativas = Arrays.asList(alternativaA, alternativaB, alternativaC, alternativaD);
        Questao novaQuestao = new Questao(enunciado, alternativas, correta, dificuldade, "Assunto Padrão");

        GerenciadorBanco.adicionarQuestao(novaQuestao);

        // Notifica todas as Views sobre a nova questão
        Questao.notificarObservers();

        System.out.println("Questão adicionada com sucesso!");
        voltarConfiguracoes();
    }

    @FXML
    public void voltarConfiguracoes() {
        ConfiguracaoView configuracaoView = new ConfiguracaoView();
        configuracaoView.initConfiguracaoView((Stage) btnCancelar.getScene().getWindow(), jogador);
        System.out.println("Voltando para a tela de configurações...");
    }

    @Override
    public void update() {
        System.out.println("AdicionarQuestaoViewController: Uma nova questão foi adicionada!");
    }
}
