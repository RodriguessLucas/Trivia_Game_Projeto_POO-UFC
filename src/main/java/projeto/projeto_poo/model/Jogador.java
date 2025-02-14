package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;

public class Jogador {
    private ArrayList<Observer> observers = new ArrayList<>();
    private String nome;

    public Jogador() {}
    public Jogador(String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (!nome.isEmpty() && (!nome.equalsIgnoreCase("Ex: astuto")) && (!nome.matches("\\s*")) ) {
            this.nome = nome;
        }
        else{
            this.nome = "Jogador";
        }
        notifica();
    }

    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifica() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
