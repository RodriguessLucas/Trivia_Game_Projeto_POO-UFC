package projeto.projeto_poo.model;

import java.util.Arrays;
import java.util.List;

public class Questao {
    private int id;
    private String assunto;
    private String pergunta;
    private List<String> alternativas;
    private Dificuldade dificuldade;
    private int correta;

    public Questao() {}
    public Questao(String enunciado, List<String> alternativas, int correta,  Dificuldade dificuldade, String assunto) {
        this.pergunta = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
        this.dificuldade = dificuldade;
        this.assunto = assunto;
    }

    public int getId() {
        return id;
    }
    public String getAssunto() { return assunto; }
    public Dificuldade getDificuldade() { return dificuldade; }
    public String getPergunta() { return pergunta; }
    public List<String> getAlternativas() { return alternativas; }
    public int getCorreta() { return correta; }

    public boolean verificarResposta(int resposta) {
        return resposta == correta;
    }
}
