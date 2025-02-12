package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import java.util.HashMap;


public class ConfiguracoesJogo {
    private static int qntdQuestoesPorJogo = 10;
    private static HashMap <String , Integer> tempoDificuldadeQuestao = new HashMap<>();
    private static HashMap <String , Integer> pontuacaoDificuldadeQuestao = new HashMap<>();

    static{
        tempoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 20);
        tempoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 30);
        tempoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 40);
        tempoDificuldadeQuestao.put("Aleatoria", 30);

        pontuacaoDificuldadeQuestao.put(Dificuldade.FACIL.getDescricao(), 50);
        pontuacaoDificuldadeQuestao.put(Dificuldade.MEDIO.getDescricao(), 100);
        pontuacaoDificuldadeQuestao.put(Dificuldade.DIFICIL.getDescricao(), 200);
    }

    public int getQntdQuestoesPorJogo() {return qntdQuestoesPorJogo;}
    public void setQntdQuestoesPorJogo(int qntdQuestoes) {this.qntdQuestoesPorJogo = qntdQuestoes; }

    public HashMap <String, Integer> getTempoDificuldadeQuestao() {return tempoDificuldadeQuestao;}

    public void setTempoDificuldadeQuestao(String dificuldade, int tempo){
        tempoDificuldadeQuestao.put(dificuldade, tempo);
    }

    public void setPontuacaoDificuldadeQuestao(String dificuldade, int tempo){
        pontuacaoDificuldadeQuestao.put(dificuldade, tempo);
    }

    public static void adicionarQuestao(Questao questao) {
        GerenciadorBanco.adicionarQuestao(questao);
    }



}
