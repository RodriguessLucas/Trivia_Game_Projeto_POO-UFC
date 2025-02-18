package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.Map;

public class Estatistica {
    private int maiorSequenciaAcerto;
    private String assuntoMaiorAcerto;
    private String assuntoMenorAcerto;
    private int maiorPontuacao;
    private int totalAcertos;
    private int totalErros;
    private Map<String, Integer> acertosPorAssunto;
    private Map<String, Integer> errosPorAssunto;

    public Estatistica() {
        this.maiorSequenciaAcerto = 0;
        this.assuntoMaiorAcerto = "";
        this.assuntoMenorAcerto = "";
        this.maiorPontuacao = 0;
        this.totalAcertos = 0;
        this.totalErros = 0;
        this.acertosPorAssunto = new HashMap<>();
        this.errosPorAssunto = new HashMap<>();

        for (Assunto assunto : Assunto.values()) {
            acertosPorAssunto.put(assunto.getDescricao(), 0);
            errosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public void contabilizarAcertosAssunto(Map<String, Integer> aux) {
        for (Map.Entry<String, Integer> acerto : aux.entrySet()) {
            String chave = acerto.getKey();
            int valor = acerto.getValue();
            if (valor > 0) {
                totalAcertos += valor;
                System.out.println("Passando contabilizar acertos por assunto");
                acertosPorAssunto.put(chave, acertosPorAssunto.get(chave) + valor);
            }
        }
    }

    public void contabilizarErrosAssunto(Map<String, Integer> aux) {
        for (Map.Entry<String, Integer> erro : aux.entrySet()) {
            String chave = erro.getKey();
            int valor = erro.getValue();
            if (valor > 0) {
                totalErros += valor;
                System.out.println("Passando contabilizar erros por assunto");
                errosPorAssunto.put(chave, errosPorAssunto.get(chave) + valor);
            }
        }
    }

    public int getMaiorSequenciaAcerto() { return maiorSequenciaAcerto; }
    public void setMaiorSequenciaAcerto(int aux) {
        if (aux > maiorSequenciaAcerto) {
            maiorSequenciaAcerto = aux;
        }
    }

    public String getAssuntoMaiorAcerto() { return assuntoMaiorAcerto; }
    public void setAssuntoMaiorAcerto(String assunto) { this.assuntoMaiorAcerto = assunto; }

    public String getAssuntoMenorAcerto() { return assuntoMenorAcerto; }
    public void setAssuntoMenorAcerto(String assunto) { this.assuntoMenorAcerto = assunto; }

    public int getMaiorPontuacao() { return maiorPontuacao; }
    public void setMaiorPontuacao(int pontuacao) {
        if (pontuacao > maiorPontuacao) {
            maiorPontuacao = pontuacao;
        }
    }

    public int getTotalAcertos() { return totalAcertos; }
    public int getTotalErros() { return totalErros; }

    public void atualizarEstatisticaJogador() {
        assuntoMaiorAcerto = acertosPorAssunto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        assuntoMenorAcerto = errosPorAssunto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

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
}
