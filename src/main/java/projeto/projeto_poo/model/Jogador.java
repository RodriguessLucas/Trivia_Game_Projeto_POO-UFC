package projeto.projeto_poo.model;

import projeto.projeto_poo.view.Observer;

import java.util.ArrayList;

public class Jogador {
    private String nome;

    public Jogador() {}
    public Jogador(String nome){
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
