package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebugWin {
    private List<Questao> questoes;
    private int pontuacao;
    private int maiorSequenciaDeAcertos;
    private int sequenciaAtualDeAcertos;
    private int indiceQuestaoAtual;

    private final Map<String, Integer> acertosPorAssunto = new HashMap<>();
    private final Map<String, Integer> errosPorAssunto = new HashMap<>();

    public DebugWin() {
        this.pontuacao = 0;
        this.maiorSequenciaDeAcertos = 0;
        this.sequenciaAtualDeAcertos = 0;
        inicializarMapasDeAssuntos();
    }

    public DebugWin(Configuracoes configuracoes){
        this.pontuacao = 0;
        this.maiorSequenciaDeAcertos = 0;
        this.sequenciaAtualDeAcertos = 0;
        inicializarMapasDeAssuntos();
    }

    private void inicializarMapasDeAssuntos() {
        for (Assunto assunto : Assunto.values()) {
            acertosPorAssunto.put(assunto.getDescricao(), 0);
            errosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public int getMaiorSequenciaDeAcertos() {
        return this.maiorSequenciaDeAcertos;
    }

    public int getSequenciaAtualDeAcertos() {
        return this.sequenciaAtualDeAcertos;
    }

    public int getIndiceQuestaoAtual() {
        return indiceQuestaoAtual;
    }

    public void setIndiceQuestaoAtual(int indiceQuestaoAtual) {
        this.indiceQuestaoAtual = indiceQuestaoAtual;
    }

    public Map<String, Integer> getAcertosPorAssunto() {
        return acertosPorAssunto;
    }

    public Map<String, Integer> getErrosPorAssunto() {
        return errosPorAssunto;
    }

    public boolean temMaisQuestoes() {
        return indiceQuestaoAtual < questoes.size();
    }

    public Questao getQuestaoAtual() {
        return temMaisQuestoes() ? questoes.get(indiceQuestaoAtual) : null;
    }

    public void responderQuestao(int resposta, int pontos) {
        if (!temMaisQuestoes()) return;

        Questao questaoAtual = questoes.get(indiceQuestaoAtual);

        if (questaoAtual.verificarResposta(resposta)) {
            incrementarAcertosPorAssunto(questaoAtual.getAssunto(), pontos);
        } else {
            incrementarErrosPorAssunto(questaoAtual.getAssunto());
        }

        indiceQuestaoAtual++;
    }

    private void incrementarAcertosPorAssunto(String assunto, int pontos) {
        acertosPorAssunto.put(assunto, acertosPorAssunto.get(assunto) + 1);
        pontuacao += pontos;
        sequenciaAtualDeAcertos++;

        if (sequenciaAtualDeAcertos > maiorSequenciaDeAcertos) {
            maiorSequenciaDeAcertos = sequenciaAtualDeAcertos;
        }
    }

    private void incrementarErrosPorAssunto(String assunto) {
        errosPorAssunto.put(assunto, errosPorAssunto.get(assunto) + 1);

        if (sequenciaAtualDeAcertos > maiorSequenciaDeAcertos) {
            maiorSequenciaDeAcertos = sequenciaAtualDeAcertos;
        }

        sequenciaAtualDeAcertos = 0;
    }
}