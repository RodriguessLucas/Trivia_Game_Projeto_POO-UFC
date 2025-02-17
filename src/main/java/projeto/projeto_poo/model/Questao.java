package projeto.projeto_poo.model;
import java.util.List;

public class Questao {
    private Assunto assunto;
    private String pergunta;
    private List<String> alternativas;
    private Dificuldade dificuldade;
    private int correta;

    public Questao() {}

    public Questao(String enunciado, List<String> alternativas, int correta, Dificuldade dificuldade, String assunto) {
        this.pergunta = enunciado;
        this.alternativas = alternativas;
        this.correta = correta;
        this.dificuldade = dificuldade;
        this.assunto = Assunto.fromDescricao(assunto);
    }

    public String getAssunto() {
        return assunto.getDescricao();
    }
    public void serAssunto() {}

    public Dificuldade getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade() {}

    public String getPergunta() {
        return pergunta;
    }
    public void setPergunta() {}

    public List<String> getAlternativas() {
        return alternativas;
    }
    public void setAlternativas() {}


    public int getCorreta() {
        return correta;
    }
    public void setCorreta() {}

    public boolean verificarResposta(int resposta) {
        return resposta == correta;
    }


}
