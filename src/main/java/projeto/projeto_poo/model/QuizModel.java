package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;

public class QuizModel {
    private static final int INDICE_INICIAL = 1; // Ajusta o índice inicial como constante

    private Jogador jogador;
    private Configuracoes configuracoes;
    private GerenciadorBanco banco;
    private int indiceAtual;

    private ArrayList<Observer> observers;

    // Construtor padrão
    public QuizModel() {
        this(new GerenciadorBanco(), new Jogador(), new Configuracoes());
        new ArrayList<Observer>();
    }

    // Construtor adicional para injeção de dependências
    public QuizModel(GerenciadorBanco banco, Jogador jogador, Configuracoes configuracoes) {
        this.banco = banco;
        this.jogador = jogador;
        this.configuracoes = configuracoes;
        this.indiceAtual = INDICE_INICIAL;
    }

    // Getter e setter do jogador
    public Jogador getJogador() {
        return jogador;
    }

    public void setNomeJogador(String nome) {
        jogador.setNome(nome);
    }

    // Getter e atualização de configurações
    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public void atualizarConfiguracoes(Configuracoes novas) {
        configuracoes = novas; // Atualiza com o novo objeto
    }

    // Métodos relacionados ao quiz
    public void resetarQuiz() {
        indiceAtual = INDICE_INICIAL; // Reseta o índice para o valor inicial
    }

    public void iniciarQuizPersonalizado(Dificuldade dificuldade, Assunto assunto, int qntdQuestao, DebugWin debug){
        debug.setQuestoes(banco.obterQuestoesPersonalizadas(qntdQuestao, dificuldade, assunto));
    }




    // Método consolidando lógica de gestão de observadores
    public void gerenciarObservadores(String acao) {
        switch (acao.toLowerCase()) {
            case "adicionar":
            case "attach":

                // Log ou implementação futura para adicionar observadores
                break;
            case "notificar":
            case "update":
                // Log ou implementação futura para atualizar observadores
                break;
            default:
                throw new IllegalArgumentException("Ação inválida para gerenciamento de observadores: " + acao);
        }
    }

    public void attachObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void detachObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }
    public void notifica() {
        for (Observer o : observers) {
            o.update();
        }
    }



}