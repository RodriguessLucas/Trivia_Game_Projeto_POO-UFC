package projeto.projeto_poo.model;

import java.util.Random;

public enum Dificuldade {
    FACIL(1, "Fácil"),
    MEDIO(2, "Médio"),
    DIFICIL(3, "Difícil");

    private static final String ERRO_VALOR_INVALIDO = "Nível inválido para dificuldade: ";
    private static final String ERRO_DESCRICAO_INVALIDA = "Descrição inválida para dificuldade: ";

    private final int nivel;
    private final String descricao;

    Dificuldade(int nivel, String descricao) {
        this.nivel = nivel;
        this.descricao = descricao;
    }

    public int getNivel() {
        return nivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Dificuldade fromNivel(int nivel) {
        for (Dificuldade dificuldade : values()) {
            if (dificuldade.getNivel() == nivel) {
                return dificuldade;
            }
        }
        throw new IllegalArgumentException(ERRO_VALOR_INVALIDO + nivel);
    }

    public static Dificuldade fromDescricao(String descricao) {
        for (Dificuldade dificuldade : values()) {
            if (dificuldade.getDescricao().equalsIgnoreCase(descricao)) {
                return dificuldade;
            }
        }
        throw new IllegalArgumentException(ERRO_DESCRICAO_INVALIDA + descricao);
    }

    public static Dificuldade getDificuldadeAleatoria() {
        return obterValorAleatorio();
    }

    private static Dificuldade obterValorAleatorio() {
        Dificuldade[] dificuldades = values();
        return dificuldades[new Random().nextInt(dificuldades.length)];
    }
}