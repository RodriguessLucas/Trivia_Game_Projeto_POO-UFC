package projeto.projeto_poo.model;

import java.util.Random;

public enum Assunto {
    POO(1, "POO"),
    ESTRUTURADADOS(2, "Estruturas de Dados"),
    PYTHON(3, "Python"),
    C(4, "C"),
    JAVA(5, "Java");

    private static final String ERRO_VALOR_INVALIDO = "Valor inválido para assunto: ";
    private static final String ERRO_DESCRICAO_INVALIDA = "Descrição inválida para assunto: ";

    private final int id;
    private final String descricao;

    Assunto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Assunto fromId(int id) {
        for (Assunto assunto : values()) {
            if (assunto.getId() == id) {
                return assunto;
            }
        }
        throw new IllegalArgumentException(ERRO_VALOR_INVALIDO + id);
    }

    public static Assunto fromDescricao(String descricao) {
        for (Assunto assunto : values()) {
            if (assunto.getDescricao().equalsIgnoreCase(descricao)) {
                return assunto;
            }
        }
        throw new IllegalArgumentException(ERRO_DESCRICAO_INVALIDA + descricao);
    }

    public static Assunto getAssuntoAleatorio() {
        return obterValorAleatorio();
    }

    private static Assunto obterValorAleatorio() {
        Assunto[] valores = values();
        return valores[new Random().nextInt(valores.length)];
    }
}