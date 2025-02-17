package projeto.projeto_poo.model;

import java.util.Random;

public enum Dificuldade {
    FACIL(1, "Fácil"),
    MEDIO(2, "Médio"),
    DIFICIL(3, "Difícil");

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
        throw new IllegalArgumentException("Valor inválido para dificuldade: " + valor);
    }

    public static Dificuldade fromDescricao(String descricao) {
        for (Dificuldade d : Dificuldade.values()) {
            if (d.getDescricao().equalsIgnoreCase(descricao)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Descrição inválida para dificuldade: " + descricao);
    }

    public static Dificuldade getDificuldadeAleatoria() {
        Random random = new Random();
        Dificuldade[] dificuldades = Dificuldade.values();
        return dificuldades[random.nextInt(dificuldades.length)];
    }
}
