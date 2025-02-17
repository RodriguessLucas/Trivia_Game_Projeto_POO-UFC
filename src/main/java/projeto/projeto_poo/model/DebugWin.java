package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;
import java.util.List;


public class DebugWin {
    private List<Questao> questoes;
    private int pontuacao;
    private int questaoAtual;
    private Dificuldade dificuldade;
    private Assunto assunto;
    private Configuracoes configuracoes;
    private ArrayList<Observer> observers = new ArrayList<>();

    public DebugWin() {}

    public DebugWin(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
        GerenciadorBanco.carregarQuestoes();
        questoes = GerenciadorBanco.obterQuestoesAleatoria(configuracoes.getQntdQuestoesPorJogo());
        pontuacao = 0;
        imprimir();
    }

    public DebugWin(Configuracoes configuracoes, Dificuldade dificuldade, Assunto assunto) {
        this.configuracoes = configuracoes;
        GerenciadorBanco.carregarQuestoes();
        questoes = GerenciadorBanco.obterQuestoesPersonalizada(configuracoes.getQntdQuestoesPorJogo(), dificuldade, assunto);
        this.dificuldade = dificuldade;
        this.assunto = assunto;
        pontuacao = 0;
    }

    public int getPontuacao() { return this.pontuacao; }
    public void setPontuacao(int pontuacao) {}

    public Configuracoes getConfiguracoes() { return this.configuracoes; }
    public void setConfiguracoes(Configuracoes configuracoes) {}

    public Dificuldade getDificuldade() { return this.dificuldade; }
    public void setDificuldade(Dificuldade dificuldade) {}

    public Assunto getAssunto() { return this.assunto; }
    public void setAssunto(Assunto assunto) { this.assunto = assunto;}


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
            pontuacao+= configuracoes.getPontuacaoPorDificuldade(questao.getDificuldade().getDescricao());
            EstatisticaJogador.contabilizarAcertosAssunto(questao.getAssunto(),1 );
        }
        else{
            EstatisticaJogador.contabilizarErrosAssunto(questao.getAssunto(),1);
        }
        questaoAtual++;
        notificarObservers();

        // tem que da uma arrumada, pois está adicionando direto nas estatisticas
    }

    public void encerrarJogo(){
        EstatisticaJogador.atualizarEstatisticaJogador();
        if(pontuacao > EstatisticaJogador.getMaiorPontuacao()){
            EstatisticaJogador.setMaiorPontuacao(pontuacao);
        }
        //tem mais coisas, como maior sequencia de acerto
        System.out.println("Encerrando Jogo");
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void imprimir(){
        for(Questao questao: questoes){
            System.out.println(questao.getDificuldade().getDescricao() + questao.getPergunta()+"\n");
        }

    }



}
