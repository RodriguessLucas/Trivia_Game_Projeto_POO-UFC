package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;
import java.util.List;


public class DebugWin {
    private List<Questao> questoes;
    private int pontuacao;
    private int questaoAtual;
    private Configuracoes configuracoes;
    private ArrayList<Observer> observers = new ArrayList<>();

    public DebugWin() {}
    public DebugWin(Configuracoes configuracoes){
        this.configuracoes = configuracoes;
        questoes = new ArrayList<>();// aqui tem q chamar o GerenciadorBanco
        pontuacao = 0;
    }

    public int getPontuacao() { return this.pontuacao; }
    public void setPontuacao(int pontuacao) {}

    public void proximaQuestao(){
    }

    public void iniciarJogo(){
        this.questaoAtual = 0;
    }

    public boolean temMaisQuestao(){
        return questaoAtual < questoes.size();
    }

    public Questao getQuestaoAtual() {
        return temMaisQuestao() ? questoes.get(questaoAtual) : null;
    }

    public void responderQuestao(int resposta){
        if(!temMaisQuestao()){ return; }

        Questao questao = questoes.get(questaoAtual);
        if(questao.verificarResposta(resposta) ){
            pontuacao+= configuracoes.getTempoPorDificuldade(questao.getDificuldade().getDescricao());
            EstatisticaJogador.contabilizarAcertosAssunto(questao.getAssunto(),1 );
        }
        else{
            EstatisticaJogador.contabilizarErrosAssunto(questao.getAssunto(),1);
        }
        questaoAtual++;
    }

    public void encerrarJogo(){
        EstatisticaJogador.atualizarEstatisticaJogador();
        System.out.println("Encerrando Jogo");
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifica() {
        for (Observer o : observers) {
            o.update();
        }
    }

}
