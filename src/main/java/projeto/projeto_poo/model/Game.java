package projeto.projeto_poo.model;
import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;


public class Game {
    private Jogador jogador;
    private GerenciadorBanco gerenciadorBanco;
    private ArrayList<Observer> observers = new ArrayList<>();








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
