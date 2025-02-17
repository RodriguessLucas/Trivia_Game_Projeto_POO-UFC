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
    private static Map<String, Integer> auxAcertosPorAssunto = new HashMap<>();
    private static Map<String, Integer> auxErrosPorAssunto = new HashMap<>();

    public DebugWin() {
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

    public int getMaiorSequenciaAcerto() { return this.maiorSequenciaAcerto; }
    public void setMaiorSequenciaAcerto(int maiorSequenciaAcerto) {}
    public int getAuxSequenciaAcerto() { return this.auxSequenciaAcerto; }

    public int getIntQuestaoAtual(){
        return questaoAtual;
    }
    public void setIntQuestaoAtual(int questaoAtual){
        this.questaoAtual = questaoAtual;
    }

    public void setAuxSequenciaAcerto(int auxSequenciaAcerto) {
        this.auxSequenciaAcerto = auxSequenciaAcerto;
    }

    public Map<String, Integer> getAuxAcertosPorAssunto() {
        return auxAcertosPorAssunto;
    }
    public Map<String, Integer> getAuxErrosPorAssunto(){
        return auxErrosPorAssunto;
    }

    public boolean temMaisQuestao(){
        return questaoAtual < questoes.size();
    }

    public Questao getQuestaoAtual() {
        return temMaisQuestao() ? questoes.get(questaoAtual) : null;
    }

    public void responderQuestao(int resposta, int pontos) {
        if(!temMaisQuestao()){return;}

        Questao questao = questoes.get(questaoAtual);
        if(questao.verificarResposta(resposta) ){
            auxAcertosPorAssunto.put(questao.getAssunto(), auxAcertosPorAssunto.get(questao.getAssunto()) + 1);
            pontuacao+= pontos;
            auxSequenciaAcerto++;

            if(questaoAtual == questoes.size()-1){
                maiorSequenciaAcerto = auxSequenciaAcerto;
            }
        }
        else{
            auxErrosPorAssunto.put(questao.getAssunto(), auxErrosPorAssunto.get(questao.getAssunto()) + 1);
            if(auxSequenciaAcerto > maiorSequenciaAcerto){
                maiorSequenciaAcerto = auxSequenciaAcerto;
                auxSequenciaAcerto = 0;
            }
        }
        questaoAtual++;
    }
}
