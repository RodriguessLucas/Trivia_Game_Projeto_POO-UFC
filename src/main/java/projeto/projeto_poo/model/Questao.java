package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;
import java.util.ArrayList;
import java.util.List;

public class Questao {
    private int id;
    private String assunto;
    private String pergunta;
    private List<String> alternativas;
    private Dificuldade dificuldade;
    private int correta;
    private static List<Observer> observers = new ArrayList<>();

    public Questao() {}

    // Construtor completo para criação manual de questões
    public Questao(String enunciado, List<String> alternativas, int correta, Dificuldade dificuldade, String assunto) {
        this.pergunta = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
        this.dificuldade = dificuldade;
        this.assunto = assunto;

        notificarObservers(); // Notifica todas as Views quando uma nova questão é criada
    }

    // Construtor atualizado para receber dificuldade como String e converter para Enum
    public Questao(String enunciado, List<String> alternativas, int correta, String dificuldade, String assunto) {
        this.pergunta = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
        this.dificuldade = Dificuldade.valueOf(dificuldade.toUpperCase()); // Converte String para Enum
        this.assunto = assunto;

        notificarObservers(); // Notifica todas as Views quando uma nova questão é criada
    }

    // Métodos Getters
    public int getId() {
        return id;
    }

    public String getAssunto() {
        return assunto;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public String getPergunta() {
        return pergunta;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public int getCorreta() {
        return correta;
    }

    public boolean verificarResposta(int resposta) {
        return resposta == correta;
    }

    // Métodos do Observer
    public static void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public static void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public static void notificarObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public String toString() {
        return "Questao{" +
                "id=" + id +
                ", assunto='" + assunto + '\'' +
                ", pergunta='" + pergunta + '\'' +
                ", alternativas=" + alternativas +
                ", dificuldade=" + dificuldade +
                ", correta=" + correta +
                '}';
    }
}
