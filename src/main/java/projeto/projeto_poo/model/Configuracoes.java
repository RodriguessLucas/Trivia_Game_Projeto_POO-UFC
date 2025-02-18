package projeto.projeto_poo.model;

import java.util.HashMap;

public class Configuracoes {
    private static final int DEFAULT_QNTD_QUESTOES = 4; // Pode ser alterado para outro valor conforme necessário
    private static final int TEMPO_PADRAO = 30;
    private static final int PONTUACAO_PADRAO = 100;

    private int qntdQuestoesPorJogo = DEFAULT_QNTD_QUESTOES;
    private final HashMap<String, Integer> tempoPorDificuldadeMap;
    private final HashMap<String, Integer> pontuacaoPorDificuldadeMap;

    public Configuracoes() {
        tempoPorDificuldadeMap = new HashMap<>();
        pontuacaoPorDificuldadeMap = new HashMap<>();
        inicializarMapas();
    }

    private void inicializarMapas() {
        tempoPorDificuldadeMap.put(Dificuldade.FACIL.getDescricao(), 20);
        tempoPorDificuldadeMap.put(Dificuldade.MEDIO.getDescricao(), 30);
        tempoPorDificuldadeMap.put(Dificuldade.DIFICIL.getDescricao(), 40);
        tempoPorDificuldadeMap.put("Aleatória", TEMPO_PADRAO);

        pontuacaoPorDificuldadeMap.put(Dificuldade.FACIL.getDescricao(), 50);
        pontuacaoPorDificuldadeMap.put(Dificuldade.MEDIO.getDescricao(), 100);
        pontuacaoPorDificuldadeMap.put(Dificuldade.DIFICIL.getDescricao(), 200);
    }

    public HashMap<String, Integer> getTempoPorDificuldadeMap() {
        return tempoPorDificuldadeMap;
    }

    public HashMap<String, Integer> getPontuacaoPorDificuldadeMap() {
        return pontuacaoPorDificuldadeMap;
    }

    public int getTempoPorDificuldade(String dificuldade) {
        return tempoPorDificuldadeMap.getOrDefault(dificuldade, TEMPO_PADRAO);
    }

    public int getPontuacaoPorDificuldade(String dificuldade) {
        return pontuacaoPorDificuldadeMap.getOrDefault(dificuldade, PONTUACAO_PADRAO);
    }

    public int getQntdQuestoesPorJogo() {
        return qntdQuestoesPorJogo;
    }

    public void setTempoPorDificuldade(String dificuldade, int tempo) {
        tempoPorDificuldadeMap.put(dificuldade, tempo);
    }

    public void setPontuacaoPorDificuldade(String dificuldade, int pontuacao) {
        pontuacaoPorDificuldadeMap.put(dificuldade, pontuacao);
    }

    public void setQntdQuestoesPorJogo(int qntQuestoes) {
        this.qntdQuestoesPorJogo = qntQuestoes;
    }

    @Override
    public String toString() {
        return "Configuracoes {" +
                "\n  Quantidade de Questões por Jogo = " + qntdQuestoesPorJogo +
                "\n  Tempo por Dificuldade = " + tempoPorDificuldadeMap +
                "\n  Pontuação por Dificuldade = " + pontuacaoPorDificuldadeMap +
                "\n}";
    }
}