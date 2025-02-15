package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.projeto_poo.model.Configuracoes;
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

        // Registra a controller como observador da configuração
        configuracoes.attachObserver(this);

        atualizarValores();
    }

    @Override
    public void update() {
        System.out.println("ConfiguracaoViewController: Configurações foram alteradas!");
        atualizarValores();
    }

    public void atualizarValores() {
        txtQntQuestoes.setText(String.valueOf(configuracoes.getQntdQuestoesPorJogo()));

        txtTempoFacil.setText(String.valueOf(configuracoes.getTempoPorDificuldade("Fácil")));
        txtTempoMedio.setText(String.valueOf(configuracoes.getTempoPorDificuldade("Médio")));
        txtTempoDificil.setText(String.valueOf(configuracoes.getTempoPorDificuldade("Difícil")));
        txtTempoAleatorio.setText(String.valueOf(configuracoes.getTempoPorDificuldade("Aleatória")));

        txtPontuacaoFacil.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade("Fácil")));
        txtPontuacaoMedio.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade("Médio")));
        txtPontuacaoDificil.setText(String.valueOf(configuracoes.getPontuacaoPorDificuldade("Difícil")));
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
            configuracoes.setTempoPorDificuldade("Fácil", tempoFacil);
            configuracoes.setTempoPorDificuldade("Médio", tempoMedio);
            configuracoes.setTempoPorDificuldade("Difícil", tempoDificil);
            configuracoes.setTempoPorDificuldade("Aleatória", tempoAleatorio);
            configuracoes.setPontuacaoPorDificuldade("Fácil", pontuacaoFacil);
            configuracoes.setPontuacaoPorDificuldade("Médio", pontuacaoMedio);
            configuracoes.setPontuacaoPorDificuldade("Difícil", pontuacaoDificil);

            configuracoes.notificarObservers(); // Notifica mudanças para todas as Views

            System.out.println("Configurações salvas: " + configuracoes);
            voltarMenu();
        } catch (NumberFormatException e) {
            System.out.println("Erro: Certifique-se de inserir apenas números.");
        }
    }

    @FXML
    public void adicionarQuestao() {
        Stage stageAtual = (Stage) btnAdicionarQuestao.getScene().getWindow();

        if (stageAtual == null) {
            System.out.println("Erro: Stage está NULL ao abrir AdicionarQuestaoView.");
            return;
        }

        AdicionarQuestaoView adicionarQuestao = new AdicionarQuestaoView();
        adicionarQuestao.initAdicionarQuestaoView(stageAtual, jogador);
        System.out.println("Indo para a tela de adicionar questão...");
    }


    @FXML
    public void voltarMenu() {
        TelaMenuView telaMenu = new TelaMenuView();
        telaMenu.initTelaMenuView((Stage) btnSalvar.getScene().getWindow(), jogador);
        System.out.println("Voltando para a tela do menu.");
    }
}
