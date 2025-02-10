package projeto.projeto_poo.model;

import java.util.Arrays;

public class Questao {
    private int id;
    private String enunciado;
    private String [] alternativas;
    private int respostaCorreta;
    private Dificuldade dificuldade;
    private String assunto;

    public Questao() {}
    public Questao(int id, String enunciado, String [] alternativas, int respostaCorreta,  int dificuldade, String assunto) {
        this.id = id;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
        setDificuldade(dificuldade);
        this.assunto = assunto;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }
    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public int getRespostaCorreta() {
        return respostaCorreta;
    }
    public void setRespostaCorreta(int respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }
    public String getDescricaoDificuldade() {
        return dificuldade.getDescricao();
    }

    public int getDificuldadeInt() {
        return dificuldade.getValor();
    }
    public void setDificuldade(int dificuldade) {
        this.dificuldade = Dificuldade.fromValor(dificuldade);
    }

    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "Questao{" +
                "id=" + id +
                ", enunciado='" + enunciado + '\'' +
                ", alternativas=" + Arrays.toString(alternativas) +
                ", respostaCorreta=" + respostaCorreta +
                ", dificuldade=" + dificuldade +
                ", areaConhecimento='" + assunto + '\'' +
                '}';
    }

}
