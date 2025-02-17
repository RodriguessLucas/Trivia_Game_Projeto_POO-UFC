package projeto.projeto_poo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Jogador;

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

    private Configuracoes configuracoes;
    private ConfiguracaoView view;
    private Jogador jogador;

    public void initConfiguracaoViewController(Configuracoes configuracoes, ConfiguracaoView view, Jogador jogador) {
        this.configuracoes = configuracoes;
        this.view = view;
        this.jogador = jogador;

        configuracoes.attachObserver(this);
        atualizarValores();
    }

    @Override
    public void update() {
        atualizarValores();
    }

    public void atualizarValores() {
        txtQntQuestoes.setText(String.valueOf(configuracoes.getQntdQuestoesPorJogo()));

        txtTempoFacil.setText(String.valueOf(configuracoes.getTempoPorDificuldade(Dificuldade.FACIL.getDescricao())));
        txtTempoMedio.setText(String.valueOf(configuracoes.getTempoPorDificuldade(Dificuldade.MEDIO.getDescricao())));
        txtTempoDificil.setText(String.valueOf(configuracoes.getTempoPorDificuldade(Dificuldade.DIFICIL.getDescricao())));
        txtTempoAleatorio.setText(String.valueOf(configuracoes.getTempoPorDificuldade("Aleatória")));

        txtPontuacaoFacil.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade(Dificuldade.FACIL.getDescricao())));
        txtPontuacaoMedio.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade(Dificuldade.MEDIO.getDescricao())));
        txtPontuacaoDificil.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade(Dificuldade.DIFICIL.getDescricao())));
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

            configuracoes.setQntdQuestoesPorJogo(qntQuestoes);
            configuracoes.setTempoPorDificuldade(Dificuldade.FACIL.getDescricao(), tempoFacil);
            configuracoes.setTempoPorDificuldade(Dificuldade.MEDIO.getDescricao(), tempoMedio);
            configuracoes.setTempoPorDificuldade(Dificuldade.DIFICIL.getDescricao(), tempoDificil);
            configuracoes.setTempoPorDificuldade("Aleatória", tempoAleatorio);
            configuracoes.setPontuacaoPorDificuldade(Dificuldade.FACIL.getDescricao(), pontuacaoFacil);
            configuracoes.setPontuacaoPorDificuldade(Dificuldade.MEDIO.getDescricao(), pontuacaoMedio);
            configuracoes.setPontuacaoPorDificuldade(Dificuldade.DIFICIL.getDescricao(), pontuacaoDificil);

            configuracoes.notificarObservers();
            mostrarAlertaTemporario("Aviso", "Configurações salvas", Alert.AlertType.INFORMATION, 1);
            voltarMenu();
        } catch (NumberFormatException e) {
            mostrarAlertaTemporario("Aviso", "Erro: Certifique-se de inserir apenas números.", Alert.AlertType.INFORMATION, 1);

        }
    }

    @FXML
    public void adicionarQuestao() {
        Stage stageAtual = (Stage) btnAdicionarQuestao.getScene().getWindow();

        if (stageAtual == null) {
            return;
        }

        AdicionarQuestaoView adicionarQuestao = new AdicionarQuestaoView();
        adicionarQuestao.initAdicionarQuestaoView(stageAtual, jogador);

    }

    public static void mostrarAlertaTemporario(String titulo, String mensagem, Alert.AlertType tipo, int segundos) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.show(); // 🔥 Exibe o alerta

        // ✅ Criando um contador para fechar automaticamente
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(segundos), event -> alerta.close()));
        timeline.setCycleCount(1);
        timeline.play();
    }


    @FXML
    public void voltarMenu() {
        configuracoes.detachObserver(this);
        configuracoes.notificarObservers();
        TelaMenuView telaMenu = new TelaMenuView();
        telaMenu.initTelaMenuView((Stage) btnSalvar.getScene().getWindow(), jogador);
        System.out.println("Voltando para a tela do menu.");
    }
}
