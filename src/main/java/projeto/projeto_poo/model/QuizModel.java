package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;

import java.util.List;

public class QuizModel {
    private Jogador jogador;
    private Configuracoes configuracoes;
    private GerenciadorBanco banco;
    private int indiceQuestoes;

    public QuizModel(GerenciadorBanco banco){
        this.banco = banco;
        this.jogador = new Jogador();
        this.configuracoes = new Configuracoes();
        this.indiceQuestoes = 1;
    }

    public Jogador getJogador(){return jogador;}

    public void setNomeJogador(String nome){
        jogador.setNome(nome);
    }

    public Configuracoes getConfiguracoes(){
        return configuracoes;
    }

    public void salvarConfiguracoes(Configuracoes novas){
        configuracoes = novas;
    }

    public void resetarQuiz() {
        indiceQuestoes = 1;
    }
}