package projeto.projeto_poo.model;

import java.util.Random;

public enum Assunto {
    POO(1,"POO"),
    ESTRUTURADADOS(2, "Estruturas de Dados"),
    PYTHON(3,"Python"),
    C(4, "C"),
    JAVA(5, "Java");

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

    public static Assunto fromDescricao(String descricao) {
        for (Assunto d : Assunto.values()) {
            if (d.getDescricao().equalsIgnoreCase(descricao)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Descrição inválida para assunto: " + descricao);
    }

    public static Assunto getAssuntoAleatorio() {
        Random random = new Random();
        Assunto[] assuntos = Assunto.values();
        return assuntos[random.nextInt(assuntos.length)];
    }


}
