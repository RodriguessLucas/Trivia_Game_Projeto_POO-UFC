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

    private static final Map<String, Integer> acertosPorAssunto = new HashMap<>();
    private static final Map<String, Integer> errosPorAssunto = new HashMap<>();

    public EstatisticaJogador() {
        inicializarMapasAssuntos();
    }

    public void adicionarAcertosPorAssunto(Map<String, Integer> dadosPorAssunto) {
        atualizarMapaDeContagem(dadosPorAssunto, acertosPorAssunto, true);
    }

    public void adicionarErrosPorAssunto(Map<String, Integer> dadosPorAssunto) {
        atualizarMapaDeContagem(dadosPorAssunto, errosPorAssunto, false);
    }

    private void inicializarMapasAssuntos() {
        for (Assunto assunto : Assunto.values()) {
            acertosPorAssunto.put(assunto.getDescricao(), 0);
            errosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    private void atualizarMapaDeContagem(Map<String, Integer> dadosPorAssunto, Map<String, Integer> mapaDestino, boolean atualizarAcertos) {
        for (Map.Entry<String, Integer> entrada : dadosPorAssunto.entrySet()) {
            String chave = entrada.getKey();
            int valor = entrada.getValue();

            if (valor > 0) {
                if (atualizarAcertos) {
                    totalAcertos += valor;
                } else {
                    totalErros += valor;
                }
                mapaDestino.put(chave, mapaDestino.getOrDefault(chave, 0) + valor);
            }
        }
    }

    public static int getMaiorSequenciaAcerto() {
        return maiorSequenciaAcerto;
    }

    public static void setMaiorSequenciaAcerto(int novaSequencia) {
        if (novaSequencia > maiorSequenciaAcerto) {
            maiorSequenciaAcerto = novaSequencia;
        }
    }

    public static String getAssuntoMaiorAcerto() {
        return assuntoMaiorAcerto;
    }

    public static String getAssuntoMenorAcerto() {
        return assuntoMenorAcerto;
    }

    public static int getMaiorPontuacao() {
        return maiorPontuacao;
    }

    public static void setMaiorPontuacao(int pontuacao) {
        if (pontuacao > maiorPontuacao) {
            maiorPontuacao = pontuacao;
        }
    }

    public static int getTotalAcertos() {
        return totalAcertos;
    }

    public static int getTotalErros() {
        return totalErros;
    }

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
}