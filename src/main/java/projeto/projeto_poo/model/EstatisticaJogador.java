package projeto.projeto_poo.model;

import java.util.HashMap;
import java.util.Map;

public class EstatisticaJogador {
    private static int maiorSequenciaAcerto = 0;
    private static String assuntoMaiorAcerto = "";
    private static String assuntoMenorAcerto = "";
    private  static int maiorPontuacao = 0;
    private static int totalAcertos = 0;
    private static int totalErros = 0;
    private static Map<String, Integer> acertosPorAssunto = new HashMap<>();
    private static Map<String, Integer> errosPorAssunto = new HashMap<>();


    public EstatisticaJogador() {
        for (Assunto assunto : Assunto.values()) {
            acertosPorAssunto.put(assunto.getDescricao(), 0);
            errosPorAssunto.put(assunto.getDescricao(), 0);
        }
    }

    public void contabilizarAcertosAssunto(Map<String, Integer> aux) {
        for(Map.Entry<String, Integer> acerto : aux.entrySet()){
            String chave = acerto.getKey();
            int valor = acerto.getValue();

            if(valor>0){
                totalAcertos += valor;
                System.out.println("Passando contabilizar acertos por assunto");
                acertosPorAssunto.put(chave, acertosPorAssunto.get(chave) + valor);
            }
        }
    }

    public void contabilizarErrosAssunto(Map<String, Integer> aux) {

        for(Map.Entry<String, Integer> erro : aux.entrySet()){
            String chave = erro.getKey();
            int valor = erro.getValue();

            if(valor>0){
                totalErros += valor;
                System.out.println("Passando contabilizar erros por assunto");
                errosPorAssunto.put(chave, acertosPorAssunto.get(chave) + valor);

            }
        }
    }

    public static int getMaiorSequenciaAcerto() { return maiorSequenciaAcerto; }
    public static void setMaiorSequenciaAcerto(int aux) {
        if(aux > maiorSequenciaAcerto){
            maiorSequenciaAcerto = aux;
        }
    }

    public static String getAssuntoMaiorAcerto() { return assuntoMaiorAcerto; }
    public static void setAssuntoMaiorAcerto() {  }

    public static String getAssuntoMenorAcerto() { return assuntoMenorAcerto; }
    public static void setAssuntoMenorAcerto() { }

    public static int getMaiorPontuacao() { return maiorPontuacao; }
    public static void setMaiorPontuacao(int pontuacao) {
        if(pontuacao > maiorPontuacao){
            maiorPontuacao = pontuacao;
        }
    }

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

}
