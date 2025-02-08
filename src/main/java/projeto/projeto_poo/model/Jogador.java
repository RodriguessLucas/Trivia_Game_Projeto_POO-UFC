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
        if (nome.isEmpty() || nome.equalsIgnoreCase("Ex: 20comer70fugir") || nome.matches("\\s*")) {
            this.nome = nome;
        }
        this.nome = "Jogador";
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
