package projeto.projeto_poo.model;

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
        if (!nome.isEmpty() && (!nome.equalsIgnoreCase("Ex: astuto")) && (!nome.matches("\\s*")) ) {
            this.nome = nome;
        }
        else{
            this.nome = "Jogador";
        }
    }
}
