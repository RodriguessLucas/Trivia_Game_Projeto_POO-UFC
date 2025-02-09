package projeto.projeto_poo.model;

public class Questao {
    private String enunciado;
    private String [] alternativas;
    private Dificuldade dificuldade;
    private String assunto;

    public Questao() {}
    public Questao(String enunciado, String [] alternativas, int dificuldade, String assunto) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        setDificuldade(dificuldade);
        this.assunto = assunto;
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

}
