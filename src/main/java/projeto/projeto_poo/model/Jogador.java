package projeto.projeto_poo.model;

public class Jogador {
    private String nome;

    public Jogador(){
        nome = "Jogador";
    }

    public Jogador(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do jogador não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim();
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
}