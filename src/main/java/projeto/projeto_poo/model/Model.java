package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;
import java.util.*;

public class Model {
    private static Model instancia;
    private Configuracoes configuracoes;
    private Jogador jogador;
    private List<Observer> observers = new ArrayList<>();

    private Model() {
        GerenciadorBanco.carregarQuestoes();
        inicializarConfiguracoes();

    }

    public void inicializarConfiguracoes() {
        this.jogador = new Jogador();
        this.configuracoes = Configuracoes.getInstancia(jogador);
    }

    public static Model getInstancia() {
        if (instancia == null) {
            instancia = new Model();
        }
        return instancia;
    }

    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }


    public String getNomeJogador() {
        return jogador.getNome();
    }
    public void setNomeJogador(String nome) {
        this.jogador.setNome(nome);
    }
    public void setNomeJogadorPadrao() {
        this.jogador.setNome("Jogador");
    }


    public List<Questao> obterQuestoesAleatorias(int quantidade) {
        return GerenciadorBanco.obterQuestoesAleatoria(quantidade);
    }

    public List<Questao> obterQuestoesPersonalizadas(Dificuldade dificuldade, Assunto assunto, int quantidade) {
        return GerenciadorBanco.obterQuestoesPersonalizada(quantidade, dificuldade, assunto);
    }

    public void adicionarObservador(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removerObservador(Observer observer) {
        observers.remove(observer);
    }

    private void notificarObservadores() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
