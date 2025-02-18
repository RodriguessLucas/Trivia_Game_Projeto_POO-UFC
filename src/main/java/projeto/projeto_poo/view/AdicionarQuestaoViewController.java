package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Jogador;
import projeto.projeto_poo.model.Questao;
import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.repository.GerenciadorBanco;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    // Agora o GerenciadorBanco será utilizado como uma instância
    private final GerenciadorBanco gerenciadorBanco;

    // Adicionamos um construtor para inicializar o GerenciadorBanco
    public AdicionarQuestaoViewController() {
        this.gerenciadorBanco = new GerenciadorBanco(); // Instanciando o gerenciador
    }

    public void initAdicionarQuestaoViewController(Jogador jogador, AdicionarQuestaoView view) {
        this.jogador = jogador;
        this.view = view;

        configurarChoiceBoxes();
        configurarToggleGroup();
    }

    private void configurarChoiceBoxes() {
        choiceDificuldade.getItems().addAll("Fácil", "Médio", "Difícil");
        choiceDificuldade.setValue(null); // Em vez de "Nenhum", usamos null como padrão.

        choiceAssunto.getItems().addAll("POO", "Estruturas de dados", "Python", "C", "Java");
        choiceAssunto.setValue(null); // Em vez de "Nenhum", usamos null como padrão.
    }

    private void configurarToggleGroup() {
        toggleCorreta = new ToggleGroup();
        radioAlternativaA.setToggleGroup(toggleCorreta);
        radioAlternativaB.setToggleGroup(toggleCorreta);
        radioAlternativaC.setToggleGroup(toggleCorreta);
        radioAlternativaD.setToggleGroup(toggleCorreta);
    }

    @FXML
    public void salvarQuestao() {
        Optional<Questao> questaoOpt = validarCampos();
        if (questaoOpt.isEmpty()) return;

        Questao novaQuestao = questaoOpt.get();

        try {
            gerenciadorBanco.adicionarQuestao(novaQuestao); // Agora utilizando a instância
            //novaQuestao.notificarObservers();
            mostrarAlertaTemporario("Aviso", "Questão salva com sucesso! Voltando para configurações...", Alert.AlertType.INFORMATION, 2);
            voltarConfiguracoes();
        } catch (Exception e) {
            mostrarAlertaTemporario("Erro", "Houve um erro ao salvar a questão no banco!", Alert.AlertType.ERROR, 2);
        }
    }

    private Optional<Questao> validarCampos() {
        // Obtendo textos dos campos
        String enunciado = txtEnunciado.getText().trim();
        String alternativaA = txtAlternativaA.getText().trim();
        String alternativaB = txtAlternativaB.getText().trim();
        String alternativaC = txtAlternativaC.getText().trim();
        String alternativaD = txtAlternativaD.getText().trim();

        // Validando dificuldade e assunto
        Dificuldade dificuldadeEnum = obterDificuldade();
        if (dificuldadeEnum == null) {
            mostrarAlertaTemporario("Aviso", "Selecione uma dificuldade!", Alert.AlertType.INFORMATION, 2);
            return Optional.empty();
        }

        Assunto assuntoEnum = obterAssunto();
        if (assuntoEnum == null) {
            mostrarAlertaTemporario("Aviso", "Selecione um assunto!", Alert.AlertType.INFORMATION, 2);
            return Optional.empty();
        }

        // Validando campos de texto
        if (enunciado.isEmpty() || alternativaA.isEmpty() || alternativaB.isEmpty() ||
                alternativaC.isEmpty() || alternativaD.isEmpty()) {
            mostrarAlertaTemporario("Aviso", "Erro: Todos os campos devem ser preenchidos!", Alert.AlertType.INFORMATION, 2);
            return Optional.empty();
        }

        // Obtendo alternativa correta
        int correta = obterAlternativaCorreta();
        if (correta == -1) {
            mostrarAlertaTemporario("Aviso", "Erro: Selecione uma alternativa correta!", Alert.AlertType.INFORMATION, 2);
            return Optional.empty();
        }

        // Criando a questão
        List<String> alternativas = Arrays.asList(alternativaA, alternativaB, alternativaC, alternativaD);
        Questao novaQuestao = new Questao(enunciado, alternativas, correta, dificuldadeEnum, assuntoEnum.getDescricao());
        //novaQuestao.attachObserver(this);

        return Optional.of(novaQuestao);
    }

    private Dificuldade obterDificuldade() {
        String dificuldade = choiceDificuldade.getValue();
        try {
            return Dificuldade.valueOf(dificuldade.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private Assunto obterAssunto() {
        String assunto = choiceAssunto.getValue();
        try {
            return Assunto.fromDescricao(assunto);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private int obterAlternativaCorreta() {
        if (radioAlternativaA.isSelected()) return 0;
        if (radioAlternativaB.isSelected()) return 1;
        if (radioAlternativaC.isSelected()) return 2;
        if (radioAlternativaD.isSelected()) return 3;
        return -1; // Nenhuma alternativa selecionada.
    }

    @FXML
    public void voltarConfiguracoes() {
        Stage stage = null;
        if (btnCancelar.getScene() != null) {
            stage = (Stage) btnCancelar.getScene().getWindow();
        }

        if (stage == null) {
            mostrarAlertaTemporario("Erro", "Janela não está disponível para voltar!", Alert.AlertType.ERROR, 2);
            return;
        }

        ConfiguracaoView configuracaoView = new ConfiguracaoView();
        configuracaoView.initConfiguracaoView(stage, jogador);
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

        // Criando contador para fechar automaticamente o alerta
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(segundos), event -> alerta.close()));
        timeline.setCycleCount(1);
        timeline.play();
    }
}