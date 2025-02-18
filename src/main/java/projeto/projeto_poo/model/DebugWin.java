package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebugWin {
    private List<Questao> questoes;
    private int pontuacao;
    private int maiorSequenciaAcerto;
    private int auxSequenciaAcerto;
    private int questaoAtual;
    private Configuracoes configuracoes;
    private Map<String, Integer> auxAcertosPorAssunto;
    private Map<String, Integer> auxErrosPorAssunto;



    public DebugWin(List<Questao> questoes, Configuracoes configuracoes) {
        this.questoes = questoes;
        this.configuracoes = configuracoes;
        inicializarVariaveis();
    }

    private void inicializarVariaveis() {
        pontuacao = 0;
        maiorSequenciaAcerto = 0;
        auxSequenciaAcerto = 0;
        questaoAtual = 0;
        auxAcertosPorAssunto = new HashMap<>();
        auxErrosPorAssunto = new HashMap<>();
        iniciarAuxiliaresMap();
    }

    private void iniciarAuxiliaresMap() {
        for (Assunto assunto : Assunto.values()) {
            auxAcertosPorAssunto.put(assunto.getDescricao(), 0);
            auxErrosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }
    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }


    public int getPontuacao() {
        return pontuacao;
    }

    public int getMaiorSequenciaAcerto() {
        return maiorSequenciaAcerto;
    }


    public int getQuestaoAtualIndex() {
        return questaoAtual;
    }

    public Map<String, Integer> getAuxAcertosPorAssunto() {
        return auxAcertosPorAssunto;
    }

    public Map<String, Integer> getAuxErrosPorAssunto() {
        return auxErrosPorAssunto;
    }

    public boolean temMaisQuestao() {
        return questaoAtual < questoes.size();
    }

    public Questao getQuestaoAtual() {
        return temMaisQuestao() ? questoes.get(questaoAtual) : null;
    }

    public void responderQuestao(int resposta) {
        if (!temMaisQuestao()) return;

        Questao questao = questoes.get(questaoAtual);
        if (questao.verificarResposta(resposta)) {
            auxAcertosPorAssunto.put(questao.getAssunto(), auxAcertosPorAssunto.get(questao.getAssunto()) + 1);
            pontuacao += configuracoes.getPontuacaoPorDificuldade(questao.getDificuldade().getDescricao());
            auxSequenciaAcerto++;

            if (questaoAtual == questoes.size() - 1) {
                maiorSequenciaAcerto = auxSequenciaAcerto;
            }
        } else {
            auxErrosPorAssunto.put(questao.getAssunto(), auxErrosPorAssunto.get(questao.getAssunto()) + 1);
            if (auxSequenciaAcerto > maiorSequenciaAcerto) {
                maiorSequenciaAcerto = auxSequenciaAcerto;
            }
            auxSequenciaAcerto = 0;
        }
        questaoAtual++;
    }
}
