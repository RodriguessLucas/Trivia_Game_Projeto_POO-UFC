package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Configuracoes {
    private static Configuracoes instancia;
    private int qntdQuestoesPorJogo = 10;
    private HashMap<String, Integer> tempoDificuldadeQuestao;
    private HashMap<String, Integer> pontuacaoDificuldadeQuestao;
    private List<Observer> observers = new ArrayList<>();

    private Configuracoes() {
        tempoDificuldadeQuestao = new HashMap<>();
        pontuacaoDificuldadeQuestao = new HashMap<>();

        tempoDificuldadeQuestao.put("Fácil", 20);
        tempoDificuldadeQuestao.put("Médio", 30);
        tempoDificuldadeQuestao.put("Difícil", 40);
        tempoDificuldadeQuestao.put("Aleatória", 30);

        pontuacaoDificuldadeQuestao.put("Fácil", 50);
        pontuacaoDificuldadeQuestao.put("Médio", 100);
        pontuacaoDificuldadeQuestao.put("Difícil", 200);
    }

    public static Configuracoes getInstancia() {
        if (instancia == null) {
            instancia = new Configuracoes();
        }
        return instancia;
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Métodos GET para recuperar os valores
    public int getTempoPorDificuldade(String dificuldade) {
        return tempoDificuldadeQuestao.getOrDefault(dificuldade, 30);
    }

    public int getPontuacaoPorDificuldade(String dificuldade) {
        return pontuacaoDificuldadeQuestao.getOrDefault(dificuldade, 100);
    }

    public int getQntdQuestoesPorJogo() {
        return qntdQuestoesPorJogo;
    }

    // Métodos SET para modificar os valores
    public void setTempoPorDificuldade(String dificuldade, int tempo) {
        tempoDificuldadeQuestao.put(dificuldade, tempo);
        notificarObservers();
    }

    public void setPontuacaoPorDificuldade(String dificuldade, int pontuacao) {
        pontuacaoDificuldadeQuestao.put(dificuldade, pontuacao);
        notificarObservers();
    }

    public void setQntdQuestoesPorJogo(int qntQuestoes) {
        this.qntdQuestoesPorJogo = qntQuestoes;
        notificarObservers();
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
