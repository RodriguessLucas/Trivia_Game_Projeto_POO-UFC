package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Configuracoes {
    private static Configuracoes instancia;
    private int qntdQuestoesPorJogo = 4; // altarar para 10 depois
    private HashMap<String, Integer> tempoDificuldadeQuestao;
    private HashMap<String, Integer> pontuacaoDificuldadeQuestao;
    private List<Observer> observers = new ArrayList<>();

    private Configuracoes() {
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

    public static Configuracoes getInstancia() {
        if (instancia == null) {
            instancia = new Configuracoes();
        }
        return instancia;
    }

    public void attachObserver(Observer observer) {
        if(observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
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
