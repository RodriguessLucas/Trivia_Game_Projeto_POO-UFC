package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.Map;

public class EstatisticaJogador {
    private int maiorSequenciaAcerto;
    private String assuntoMaiorAcerto;
    private String assuntoMenorAcerto;
    private int maiorPontuacao;
    private int totalAcertos;
    private int totalErros;
    HashMap <String, Integer> acertosPorAssunto;
    HashMap <String, Integer> errosPorAssunto;

    public EstatisticaJogador(){
        this.maiorSequenciaAcerto = 0;
        this.assuntoMaiorAcerto = "";
        this.assuntoMenorAcerto = "";
        this.maiorPontuacao = 0;
        this.totalAcertos = 0;
        this.totalErros = 0;
        this.acertosPorAssunto = new HashMap<>();
        this.errosPorAssunto = new HashMap<>();

        for(Assunto assunto: Assunto.values()){
            acertosPorAssunto.put(assunto.getDescricao(),0);
            errosPorAssunto.put(assunto.getDescricao(),0);
        }
    }


    public void contabilizarAcertosAssunto(String assunto, int pontos){
        acertosPorAssunto.put(assunto, acertosPorAssunto.get(assunto)+pontos);
        totalAcertos += pontos;
    }

    public void contabilizarErrosAssunto(String assunto, int pontos){
        errosPorAssunto.put(assunto, acertosPorAssunto.get(assunto)+pontos);
        totalAcertos += pontos;
    }


    public int getMaiorSequenciaAcerto() {
        return maiorSequenciaAcerto;
    }
    public void setMaiorSequenciaAcerto(int maiorSequenciaAcerto) {
        this.maiorSequenciaAcerto = maiorSequenciaAcerto;
    }

    public String getAssuntoMaiorAcerto() {
        return assuntoMaiorAcerto;
    }
    public void setAssuntoMaiorAcerto(String assuntoMaiorAcerto) {
        this.assuntoMaiorAcerto = assuntoMaiorAcerto;
    }

    public String getAssuntoMenorAcerto() {
        return assuntoMenorAcerto;
    }
    public void setAssuntoMenorAcerto(String assuntoMenorAcerto) {
        this.assuntoMenorAcerto = assuntoMenorAcerto;
    }

    public int getMaiorPontuacao() {
        return maiorPontuacao;
    }
    public void setMaiorPontuacao(int maiorPontuacao) {
        this.maiorPontuacao = maiorPontuacao;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }
    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public int getTotalErros() {
        return totalErros;
    }
    public void setTotalErros(int totalErros) { this.totalErros = totalErros; }

    @Override
    public String toString() {
        return "{\n" +
                "  \"maiorSequenciaAcerto\": " + maiorSequenciaAcerto + ",\n" +
                "  \"assuntoMaiorAcerto\": \"" + assuntoMaiorAcerto + "\",\n" +
                "  \"assuntoMenorAcerto\": \"" + assuntoMenorAcerto + "\",\n" +
                "  \"maiorPontuacao\": " + maiorPontuacao + ",\n" +
                "  \"totalAcertos\": " + totalAcertos + ",\n" +
                "  \"totalErros\": " + totalErros + ",\n" +
                "  \"acertosPorAssunto\": " + acertosPorAssunto + ",\n" +
                "  \"errosPorAssunto\": " + errosPorAssunto + "\n" +
                "}";
    }

    public void atualizarEstatisticaJogador(){
        assuntoMaiorAcerto = acertosPorAssunto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        assuntoMenorAcerto = errosPorAssunto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

    }

}
