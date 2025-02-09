package projeto.projeto_poo.model;

public enum Dificuldade {
    FACIL(1,"Facil"),
    MEDIO(2, "Medio"),
    DIFICIL(3, "Dificil"),;

    private final int valor;
    private final String descricao;

    private Dificuldade(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Dificuldade fromValor(int valor) {
        for (Dificuldade d : Dificuldade.values()) {
            if (d.getValor() == valor) {
                return d;
            }
        }
        throw new IllegalArgumentException("Valor invalido para dificuldade: " + valor);
    }

}
