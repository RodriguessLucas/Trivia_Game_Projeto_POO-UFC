package projeto.projeto_poo.model;

import java.util.Objects;

public class Jogador {
    private String nome;
    private int pontuacao;
    private int pontuacaoMaxima;

    public Jogador() {}
    public Jogador(String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome.isEmpty() ? nome : nome.toUpperCase();
    }
    public int getPontuacao() {
        return pontuacao;
    }
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
    public int getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }
    public void setPontuacaoMaxima(int pontuacaoMaxima) {
        this.pontuacaoMaxima = pontuacaoMaxima;
    }

}
