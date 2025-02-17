package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DebugWin {
    private List<Questao> questoes;
    private int pontuacao;
    private int maiorSequenciaAcerto;
    private int auxSequenciaAcerto;
    private int questaoAtual;
    private Dificuldade dificuldade;
    private Assunto assunto;
    private Configuracoes configuracoes;
    private static Map<String, Integer> auxAcertosPorAssunto = new HashMap<>();
    private static Map<String, Integer> auxErrosPorAssunto = new HashMap<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    public DebugWin() {}

    public DebugWin(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
        GerenciadorBanco.carregarQuestoes();
        questoes = GerenciadorBanco.obterQuestoesAleatoria(configuracoes.getQntdQuestoesPorJogo());
        pontuacao = 0;
        maiorSequenciaAcerto = 0;
        auxSequenciaAcerto = 0;
        iniciarAuxiliaresMap();
    }

    public DebugWin(Configuracoes configuracoes, Dificuldade dificuldade, Assunto assunto) {
        this.configuracoes = configuracoes;
        GerenciadorBanco.carregarQuestoes();
        questoes = GerenciadorBanco.obterQuestoesPersonalizada(configuracoes.getQntdQuestoesPorJogo(), dificuldade, assunto);
        this.dificuldade = dificuldade;
        this.assunto = assunto;
        pontuacao = 0;
        maiorSequenciaAcerto = 0;
        auxSequenciaAcerto = 0;
        iniciarAuxiliaresMap();
    }

    private void iniciarAuxiliaresMap(){
        for (Assunto assunto : Assunto.values()) {
            auxAcertosPorAssunto.put(assunto.getDescricao(), 0);
            auxErrosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public int getPontuacao() { return this.pontuacao; }
    public void setPontuacao(int pontuacao) {}

    public Configuracoes getConfiguracoes() { return this.configuracoes; }
    public void setConfiguracoes(Configuracoes configuracoes) {}

    public Dificuldade getDificuldade() { return this.dificuldade; }
    public void setDificuldade(Dificuldade dificuldade) {}

    public Assunto getAssunto() { return this.assunto; }
    public void setAssunto(Assunto assunto) { this.assunto = assunto;}

    public int getMaiorSequenciaAcerto() { return this.maiorSequenciaAcerto; }
    public void setMaiorSequenciaAcerto(int maiorSequenciaAcerto) {}

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
            auxAcertosPorAssunto.put(questao.getAssunto(), auxAcertosPorAssunto.get(questao.getAssunto()) + 1);
            pontuacao+= configuracoes.getPontuacaoPorDificuldade(questao.getDificuldade().getDescricao());
            auxSequenciaAcerto++;
        }
        else{
            auxErrosPorAssunto.put(questao.getAssunto(), auxErrosPorAssunto.get(questao.getAssunto()) + 1);
            if(auxSequenciaAcerto > maiorSequenciaAcerto){
                maiorSequenciaAcerto = auxSequenciaAcerto;
                auxSequenciaAcerto = 0;
            }
        }
        questaoAtual++;
        notificarObservers();
    }

    public void encerrarJogo(){
        EstatisticaJogador.setMaiorPontuacao(maiorSequenciaAcerto);
        if(pontuacao > EstatisticaJogador.getMaiorPontuacao()){
            EstatisticaJogador.setMaiorPontuacao(pontuacao);
        }
        EstatisticaJogador.setMaiorSequenciaAcerto(maiorSequenciaAcerto);
        EstatisticaJogador.contabilizarAcertosAssunto(auxAcertosPorAssunto);
        EstatisticaJogador.contabilizarErrosAssunto(auxErrosPorAssunto);
        EstatisticaJogador.atualizarEstatisticaJogador();
        auxAcertosPorAssunto.clear(); auxErrosPorAssunto.clear();
        //System.out.println("Encerrando Jogo"); notificar tela
    }

    public void attachObserver(Observer observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        if(observers.isEmpty()){
            System.out.println("Nenhum Observer foi encontrado");
            return;
        }
        for (Observer o : observers) {
            o.update();
        }
    }

}
