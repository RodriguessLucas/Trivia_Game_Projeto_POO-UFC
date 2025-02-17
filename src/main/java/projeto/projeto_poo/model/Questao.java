package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;
import java.util.ArrayList;
import java.util.List;

public class Questao {
    private int id;
    private Assunto assunto;
    private String pergunta;
    private List<String> alternativas;
    private Dificuldade dificuldade;
    private int correta;
    private  List<Observer> observers = new ArrayList<>();

    public Questao() {}

    public Questao(String enunciado, List<String> alternativas, int correta, String dificuldade, String assunto) {
        this.pergunta = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
        this.dificuldade = Dificuldade.fromDescricao(dificuldade);
        this.assunto = Assunto.fromDescricao(assunto);

        notificarObservers();
    }

    public int getId() {
        return id;
    }

    public String getAssunto() {
        return assunto.getDescricao();
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

    public void attachObserver(Observer observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }
    public  void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public  void notificarObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public String toString() {
        return "Questao{" +
                "id=" + id +
                ", assunto='" + assunto.getDescricao() + '\'' +
                ", pergunta='" + pergunta + '\'' +
                ", alternativas=" + alternativas +
                ", dificuldade=" + dificuldade.getDescricao() +
                ", correta=" + correta +
                '}';
    }
}
