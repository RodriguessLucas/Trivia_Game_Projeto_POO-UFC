package projeto.projeto_poo.model;

public enum Assunto {
    POO(1,"Programacao orientada a objetos"),
    ED(2, "Estruturas de dados"),
    GRAFOS(3,"Grafos"),
    MATDISC(4, "Matematica Discreta"),
    ARQCOMP(5, "Arquitetura de Computadores");

    private int id;
    private String descricao;

    private Assunto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }

    public static Assunto fromValor(int valor) {
        for (Assunto assunto : values()) {
            if (assunto.getId() == valor) {
                return assunto;
            }
        }
        throw new IllegalArgumentException("Valor invalido para assunto " + valor);
    }

}
