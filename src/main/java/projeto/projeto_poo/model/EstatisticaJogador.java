package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.Map;

public class EstatisticaJogador {
    private static int maiorSequenciaAcerto = 0;
    private static String assuntoMaiorAcerto = "";
    private static String assuntoMenorAcerto = "";
    private static int maiorPontuacao = 0;
    private static int totalAcertos = 0;
    private static int totalErros = 0;
    private static Map<String, Integer> acertosPorAssunto = new HashMap<>();
    private static Map<String, Integer> errosPorAssunto = new HashMap<>();

    static {
        for (Assunto assunto : Assunto.values()) {
            acertosPorAssunto.put(assunto.getDescricao(), 0);
            errosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public static void contabilizarAcertosAssunto(String assunto, int pontos) {
        acertosPorAssunto.put(assunto, acertosPorAssunto.getOrDefault(assunto, 0) + pontos);
        totalAcertos += pontos;
    }

    public static void contabilizarErrosAssunto(String assunto, int pontos) {
        errosPorAssunto.put(assunto, errosPorAssunto.getOrDefault(assunto, 0) + pontos);
        totalErros += pontos;
    }

    public static int getMaiorSequenciaAcerto() { return maiorSequenciaAcerto; }
    public static void setMaiorSequenciaAcerto(int maiorSequencia) { maiorSequenciaAcerto = maiorSequencia; }

    public static String getAssuntoMaiorAcerto() { return assuntoMaiorAcerto; }
    public static void setAssuntoMaiorAcerto(String assunto) { assuntoMaiorAcerto = assunto; }

    public static String getAssuntoMenorAcerto() { return assuntoMenorAcerto; }
    public static void setAssuntoMenorAcerto(String assunto) { assuntoMenorAcerto = assunto; }

    public static int getMaiorPontuacao() { return maiorPontuacao; }
    public static void setMaiorPontuacao(int pontuacao) { maiorPontuacao = pontuacao; }

    public static int getTotalAcertos() { return totalAcertos; }
    public static int getTotalErros() { return totalErros; }

    public static void atualizarEstatisticaJogador() {
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
