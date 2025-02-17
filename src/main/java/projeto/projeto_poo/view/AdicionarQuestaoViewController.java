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
    @FXML private ChoiceBox<String> choiceAssunto;
    @FXML private TextField txtAlternativaA;
    @FXML private TextField txtAlternativaB;
    @FXML private TextField txtAlternativaC;
    @FXML private TextField txtAlternativaD;
    @FXML private RadioButton radioAlternativaA;
    @FXML private RadioButton radioAlternativaB;
    @FXML private RadioButton radioAlternativaC;
    @FXML private RadioButton radioAlternativaD;
    @FXML private Button btnSalvarQuestao;
    @FXML private Button btnCancelar;

    private Jogador jogador;
    private AdicionarQuestaoView view;
    private ToggleGroup toggleCorreta;


    public void initAdicionarQuestaoViewController(Jogador jogador, AdicionarQuestaoView view) {
        this.jogador = jogador;
        this.view = view;

        choiceDificuldade.getItems().addAll("Fácil", "Médio", "Difícil");
        choiceDificuldade.setValue("Nenhum");

        choiceAssunto.getItems().addAll("POO", "Estruturas de dados", "Python", "C","Java");
        choiceAssunto.setValue("Nenhum");

        toggleCorreta = new ToggleGroup();
        radioAlternativaA.setToggleGroup(toggleCorreta);
        radioAlternativaB.setToggleGroup(toggleCorreta);
        radioAlternativaC.setToggleGroup(toggleCorreta);
        radioAlternativaD.setToggleGroup(toggleCorreta);

    }

    @FXML
    public void salvarQuestao() {  // tem q por aqui o retorno q será um alerta na tela
        String enunciado = txtEnunciado.getText().trim();
        String dificuldade = choiceDificuldade.getValue();
        String assunto = choiceAssunto.getValue();
        String alternativaA = txtAlternativaA.getText().trim();
        String alternativaB = txtAlternativaB.getText().trim();
        String alternativaC = txtAlternativaC.getText().trim();
        String alternativaD = txtAlternativaD.getText().trim();

        if(dificuldade.equals("Nenhum")) {
            System.out.println("Selecione uma dificuldade!");
            return;
        }

        if(assunto.equals("Nenhum")) {
            System.out.println("Selecione um assunto!");
            return;
        }

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

        if (enunciado.isEmpty() || alternativaA.isEmpty() || alternativaB.isEmpty() ||
                alternativaC.isEmpty() || alternativaD.isEmpty()) {
            System.out.println("Erro: Todos os campos devem ser preenchidos!");
            return;
        }

        List<String> alternativas = Arrays.asList(alternativaA, alternativaB, alternativaC, alternativaD);
        Questao novaQuestao = new Questao(enunciado, alternativas, correta, dificuldade, assunto);

        novaQuestao.attachObserver(this);
        GerenciadorBanco.adicionarQuestao(novaQuestao); // tem que verificar aqui
        novaQuestao.notificarObservers();
        GerenciadorBanco.imprimirQuestoes();

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
