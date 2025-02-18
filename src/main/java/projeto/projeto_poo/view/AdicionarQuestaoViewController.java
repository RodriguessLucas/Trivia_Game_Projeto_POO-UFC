package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import projeto.projeto_poo.model.Model;
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

    private Model model;
    private AdicionarQuestaoView view;
    private ToggleGroup toggleCorreta;

    public void initAdicionarQuestaoViewController(Model model, AdicionarQuestaoView view) {
        this.model = model;
        this.view = view;
        model.adicionarObservador(this);

        choiceDificuldade.getItems().addAll("Fácil", "Médio", "Difícil");
        choiceDificuldade.setValue("Nenhum");

        choiceAssunto.getItems().addAll("POO", "Estruturas de dados", "Python", "C", "Java");
        choiceAssunto.setValue("Nenhum");

        toggleCorreta = new ToggleGroup();
        radioAlternativaA.setToggleGroup(toggleCorreta);
        radioAlternativaB.setToggleGroup(toggleCorreta);
        radioAlternativaC.setToggleGroup(toggleCorreta);
        radioAlternativaD.setToggleGroup(toggleCorreta);
    }

    @FXML
    public void salvarQuestao() {
        String enunciado = txtEnunciado.getText().trim();
        String dificuldade = choiceDificuldade.getValue();
        String assunto = choiceAssunto.getValue();
        String alternativaA = txtAlternativaA.getText().trim();
        String alternativaB = txtAlternativaB.getText().trim();
        String alternativaC = txtAlternativaC.getText().trim();
        String alternativaD = txtAlternativaD.getText().trim();

        if (dificuldade.equals("Nenhum")) {
            mostrarAlertaTemporario("Aviso", "Selecione uma dificuldade!", Alert.AlertType.INFORMATION, 2);
            return;
        }

        if (assunto.equals("Nenhum")) {
            mostrarAlertaTemporario("Aviso", "Selecione um assunto!", Alert.AlertType.INFORMATION, 2);
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
            mostrarAlertaTemporario("Aviso", "Erro: Selecione uma alternativa correta!", Alert.AlertType.INFORMATION, 2);
            return;
        }

        if (enunciado.isEmpty() || alternativaA.isEmpty() || alternativaB.isEmpty() ||
                alternativaC.isEmpty() || alternativaD.isEmpty()) {
            mostrarAlertaTemporario("Aviso", "Erro: Todos os campos devem ser preenchidos!", Alert.AlertType.INFORMATION, 2);
            return;
        }

        List<String> alternativas = Arrays.asList(alternativaA, alternativaB, alternativaC, alternativaD);
        Questao novaQuestao = new Questao(enunciado, alternativas, correta, dificuldade, assunto);
        // tem que arrumar isso
        model.adicionarQuestaoBancoQuestao(novaQuestao);
        mostrarAlertaTemporario("Aviso", "Questão salva com sucesso! Voltando para configurações...", Alert.AlertType.INFORMATION, 2);
        voltarConfiguracoes();
    }

    @FXML
    public void voltarConfiguracoes() {
        Stage stageAtual = (Stage) btnCancelar.getScene().getWindow();
        stageAtual.close();
        model.removerObservador(this);

        ConfiguracaoView configuracaoView = new ConfiguracaoView(model);
        configuracaoView.initConfiguracaoView(new Stage());
    }

    @Override
    public void update() {
        System.out.println("AdicionarQuestaoViewController: Uma nova questão foi adicionada!");
    }

    private void mostrarAlertaTemporario(String titulo, String mensagem, Alert.AlertType tipo, int segundos) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(segundos), event -> alerta.close()));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
