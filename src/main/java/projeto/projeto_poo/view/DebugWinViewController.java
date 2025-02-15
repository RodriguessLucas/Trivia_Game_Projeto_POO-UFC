package projeto.projeto_poo.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import projeto.projeto_poo.model.Configuracoes;
import projeto.projeto_poo.model.DebugWin;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Questao;



public class DebugWinViewController implements Observer {

    @FXML
    private Label lblExibirTempoPorPergunta;

    @FXML
    private Label lblExibirPontuacaoQuestao;

    @FXML
    private Label lblPergunta;

    @FXML
    private Button btnLetraA;

    @FXML
    private Button btnLetraB;

    @FXML
    private Button btnLetraC;

    @FXML
    private Button btnLetraD;

    @FXML
    private Button btnPularQuestao;

    @FXML
    private Button btnDesistir;


    private DebugWin debugWinJogo;
    private DebugWinView debugWinView;

    public void initDebugWinViewController(Dificuldade dificuldade, Configuracoes config, boolean ehAleatorio){
        this.debugWinJogo = new DebugWin(config, dificuldade, ehAleatorio);
    }

    private void carregarQuestao(){
        if(!debugWinJogo.temMaisQuestao()){
            encerrarDebugWin();
            return;
        }

        Questao questaoAtual = debugWinJogo.getQuestaoAtual();
        lblPergunta.setText(questaoAtual.getPergunta());
        // da uma verificada se vai bater certinho com o xml, pois  acho q pode da erro por conta do indice
        // guilherme pois os indices do xml de 1 a 4 e não de 0 a 3
        // questao armazena um arraylist
        btnLetraA.setText(questaoAtual.getAlternativas().get(0));
        btnLetraB.setText(questaoAtual.getAlternativas().get(1));
        btnLetraC.setText(questaoAtual.getAlternativas().get(2));
        btnLetraD.setText(questaoAtual.getAlternativas().get(3));
        // essa aqui é para apresentar a pontuação, não sei se é legal deixar a pontuacao na questao ou so dps de acertar ou errar
        lblExibirPontuacaoQuestao.setText("pontuacao da questao: " + debugWinJogo.getConfiguracoes().getPontuacaoPorDificuldade(questaoAtual.getDificuldade().getDescricao()));

    }

    //aqui é a chamada ao clicar nos botoes das alternativas
    // Pensei em ser so direto na processarResposta e os botoes chamar a mesma função, mas acho q da problema
    private void responderA(){ processarResposta(1);}
    private void responderB(){ processarResposta(2);}
    private void responderC(){ processarResposta(3);}
    private void responderD(){ processarResposta(4);}

    private void processarResposta(int resposta){
        debugWinJogo.responderQuestao(resposta);
        carregarQuestao();
    }

    private void encerrarDebugWin(){
        // aqui Lucena, eu to colocando o texto de finalização do jogo na parte de
        // enunciado
        lblPergunta.setText("Questões Finalizadas!");
        lblPergunta.setText("Sua pontuação: "+ debugWinJogo.getPontuacao());
        lblExibirTempoPorPergunta.setText("");

        btnLetraA.setDisable(true);
        btnLetraB.setDisable(true);
        btnLetraC.setDisable(true);
        btnLetraD.setDisable(true);

        /* a ideia é quando exibir não poder mais mexer nos botoes das alternativas


         */


    }






    @Override
    public void update(){}
}
