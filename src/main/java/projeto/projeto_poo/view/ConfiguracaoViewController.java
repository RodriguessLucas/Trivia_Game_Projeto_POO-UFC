package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Model;
import projeto.projeto_poo.model.Dificuldade;

public class ConfiguracaoViewController implements Observer {
    @FXML private TextField txtQntQuestoes;
    @FXML private TextField txtTempoFacil;
    @FXML private TextField txtTempoMedio;
    @FXML private TextField txtTempoDificil;
    @FXML private TextField txtTempoAleatorio;
    @FXML private TextField txtPontuacaoFacil;
    @FXML private TextField txtPontuacaoMedio;
    @FXML private TextField txtPontuacaoDificil;
    @FXML private Button btnSalvar;
    @FXML private Button btnAdicionarQuestao;

    private Model model;
    private ConfiguracaoView view;

    public void initConfiguracaoViewController(Model model, ConfiguracaoView view) {
        this.model = model;
        this.view = view;
        model.adicionarObservador(this);
        atualizarValores();
    }

    @Override
    public void update() {
        atualizarValores();
    }

    public void atualizarValores() {
        txtQntQuestoes.setText(String.valueOf(model.getConfiguracoesQntdQuestoesPorJogo()));
        txtTempoFacil.setText(String.valueOf(model.getConfiguracoesTempoPorDificuldade(Dificuldade.FACIL.getDescricao())));
        txtTempoMedio.setText(String.valueOf(model.getConfiguracoesTempoPorDificuldade(Dificuldade.MEDIO.getDescricao())));
        txtTempoDificil.setText(String.valueOf(model.getConfiguracoesTempoPorDificuldade(Dificuldade.DIFICIL.getDescricao())));
        txtTempoAleatorio.setText(String.valueOf(model.getConfiguracoesTempoPorDificuldade("Aleatória")));
        txtPontuacaoFacil.setText(String.valueOf(model.getConfiguracoesPontuacaoPorDificuldade(Dificuldade.FACIL.getDescricao())));
        txtPontuacaoMedio.setText(String.valueOf(model.getConfiguracoesPontuacaoPorDificuldade(Dificuldade.MEDIO.getDescricao())));
        txtPontuacaoDificil.setText(String.valueOf(model.getConfiguracoesPontuacaoPorDificuldade(Dificuldade.DIFICIL.getDescricao())));
    }

    @FXML
    public void salvarConfiguracoes() {
        try {
            int qntQuestoes = Integer.parseInt(txtQntQuestoes.getText());
            int tempoFacil = Integer.parseInt(txtTempoFacil.getText());
            int tempoMedio = Integer.parseInt(txtTempoMedio.getText());
            int tempoDificil = Integer.parseInt(txtTempoDificil.getText());
            int tempoAleatorio = Integer.parseInt(txtTempoAleatorio.getText());
            int pontuacaoFacil = Integer.parseInt(txtPontuacaoFacil.getText());
            int pontuacaoMedio = Integer.parseInt(txtPontuacaoMedio.getText());
            int pontuacaoDificil = Integer.parseInt(txtPontuacaoDificil.getText());

            model.setConfiguracoesQntdQuestoesPorJogo(qntQuestoes);
            model.setConfiguracoesTempoPorDificuldade(Dificuldade.FACIL.getDescricao(), tempoFacil);
            model.setConfiguracoesTempoPorDificuldade(Dificuldade.MEDIO.getDescricao(), tempoMedio);
            model.setConfiguracoesTempoPorDificuldade(Dificuldade.DIFICIL.getDescricao(), tempoDificil);
            model.setConfiguracoesTempoPorDificuldade("Aleatória", tempoAleatorio);
            model.setConfiguracoesPontuacaoPorDificuldade(Dificuldade.FACIL.getDescricao(), pontuacaoFacil);
            model.setConfiguracoesPontuacaoPorDificuldade(Dificuldade.MEDIO.getDescricao(), pontuacaoMedio);
            model.setConfiguracoesPontuacaoPorDificuldade(Dificuldade.DIFICIL.getDescricao(), pontuacaoDificil);

            mostrarAlerta("Configurações salvas com sucesso!", Alert.AlertType.INFORMATION);
            voltarMenu();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro: Certifique-se de inserir apenas números.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void adicionarQuestao() {
        Stage stageAtual = (Stage) btnAdicionarQuestao.getScene().getWindow();
        stageAtual.close();

        AdicionarQuestaoView adicionarQuestaoView = new AdicionarQuestaoView(model);
        adicionarQuestaoView.initAdicionarQuestaoView(new Stage());
    }

    @FXML
    public void voltarMenu() {
        Stage stageAtual = (Stage) btnSalvar.getScene().getWindow();
        stageAtual.close();
        model.removerObservador(this);

        TelaMenuView telaMenu = new TelaMenuView(model);
        telaMenu.initTelaMenuView(stageAtual);
    }

    private void mostrarAlerta(String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
