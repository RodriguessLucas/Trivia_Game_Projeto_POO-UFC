package projeto.projeto_poo.model;

import java.util.HashMap;

public class Configuracoes {
    private int qntdQuestoesPorJogo = 4; // alterar para 10 depois
    private HashMap<String, Integer> tempoDificuldadeQuestao;
    private HashMap<String, Integer> pontuacaoDificuldadeQuestao;

    public Configuracoes() {
        tempoDificuldadeQuestao = new HashMap<>();
        pontuacaoDificuldadeQuestao = new HashMap<>();

        tempoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 20);
        tempoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 30);
        tempoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 40);
        tempoDificuldadeQuestao.put("Aleatória", 30);

        pontuacaoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 50);
        pontuacaoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 100);
        pontuacaoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 200);
    }

    public int getTempoPorDificuldade(String dificuldade) {
        return tempoDificuldadeQuestao.getOrDefault(dificuldade, 30);
    }

    public int getPontuacaoPorDificuldade(String dificuldade) {
        return pontuacaoDificuldadeQuestao.getOrDefault(dificuldade, 100);
    }

    public int getQntdQuestoesPorJogo() {
        return qntdQuestoesPorJogo;
    }

    public void setTempoPorDificuldade(String dificuldade, int tempo) {
        tempoDificuldadeQuestao.put(dificuldade, tempo);
    }

    public void setPontuacaoPorDificuldade(String dificuldade, int pontuacao) {
        pontuacaoDificuldadeQuestao.put(dificuldade, pontuacao);
    }

    public void setQntdQuestoesPorJogo(int qntQuestoes) {
        this.qntdQuestoesPorJogo = qntQuestoes;
    }

    public void resetarConfiguracoes() {
        qntdQuestoesPorJogo = 4;
        tempoDificuldadeQuestao.clear();
        pontuacaoDificuldadeQuestao.clear();

        tempoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 20);
        tempoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 30);
        tempoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 40);
        tempoDificuldadeQuestao.put("Aleatória", 30);

        pontuacaoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 50);
        pontuacaoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 100);
        pontuacaoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 200);
    }

    @Override
    public String toString() {
        return "Configuracoes {" +
                "\n  Quantidade de Questões por Jogo = " + qntdQuestoesPorJogo +
                "\n  Tempo por Dificuldade = " + tempoDificuldadeQuestao +
                "\n  Pontuação por Dificuldade = " + pontuacaoDificuldadeQuestao +
                "\n}";
    }
}
