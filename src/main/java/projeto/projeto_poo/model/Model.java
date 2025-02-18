package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;
import projeto.projeto_poo.view.Observer;
import java.util.*;

public class Model {
    private static Model instancia;
    private Configuracoes configuracoes;
    private Jogador jogador;
    private DebugWin debugWin;
    private Estatistica estatisticaGlobal;
    private GerenciadorBanco gerenciadorBanco;
    private List<Observer> observers = new ArrayList<>();

    private Model() {
        gerenciadorBanco = new GerenciadorBanco();
        configuracoes = new Configuracoes();
        estatisticaGlobal = new Estatistica();
        jogador = new Jogador();

    }

    public static Model getInstancia() {
        if (instancia == null) {
            instancia = new Model();
        }
        return instancia;
    }



    /*
        MÉTODOS PARA CONFIGURAÇÕES
     */
    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public int getConfiguracoesTempoPorDificuldade(String dificuldade) {
        return configuracoes.getTempoPorDificuldade( dificuldade);
    }
    public void setConfiguracoesTempoPorDificuldade(String Dificuldade, int tempo) {
        configuracoes.setTempoPorDificuldade(Dificuldade, tempo);
    }

    public int getConfiguracoesPontuacaoPorDificuldade(String dificuldade) {
        return configuracoes.getPontuacaoPorDificuldade(dificuldade);
    }
    public void setConfiguracoesPontuacaoPorDificuldade(String Dificuldade, int pontuacao) {
        configuracoes.setPontuacaoPorDificuldade(Dificuldade, pontuacao);
    }

    public int getConfiguracoesQntdQuestoesPorJogo(){
        return configuracoes.getQntdQuestoesPorJogo();
    }
    public void setConfiguracoesQntdQuestoesPorJogo(int qntd) {
        configuracoes.setQntdQuestoesPorJogo(qntd);
    }

    public void setConfiguracoesResetar(){
        configuracoes.resetarConfiguracoes();
    }



    /*
        MÉTODOS PARA JOGADOR
     */
    public Jogador getJogador() {
        return jogador;
    }
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getNomeJogador() {
        return jogador.getNome();
    }
    public void setNomeJogador(String nome) {
        this.jogador.setNome(nome);
    }
    public void setNomeJogadorPadrao() {
        this.jogador.setNome("Jogador");
    }



    /*
        MÉTODOS PARA ESTATISTICA
     */
    public void estatisticaContabilizarAcertosAssuntos(Map<String, Integer> aux) {
        estatisticaGlobal.contabilizarAcertosAssunto(aux);
    }
    public void estatisticaContabilizarErrosAssuntos(Map<String, Integer> aux) {
        estatisticaGlobal.contabilizarErrosAssunto(aux);
    }

    public int getMaiorSequenciaAcerto(){
        return estatisticaGlobal.getMaiorSequenciaAcerto();
    }
    public void setMaiorSequenciaAcerto(int maiorSequenciaAcerto) {
        estatisticaGlobal.setMaiorSequenciaAcerto(maiorSequenciaAcerto);
    }

    public String getEstatisticaAssuntoMaiorAcerto(){
        return estatisticaGlobal.getAssuntoMaiorAcerto();
    }
    public String getEstatisticaAssuntoMenorAcerto(){
        return estatisticaGlobal.getAssuntoMenorAcerto();
    }

    public int getEstatisticaMaiorPontuacao(){
        return estatisticaGlobal.getMaiorPontuacao();
    }
    public void setEstatisticaMaiorPontuacao(int maiorPontuacao) {
        estatisticaGlobal.setMaiorPontuacao(maiorPontuacao);
    }

    public int getEstatisticaTotalAcertos(){
        return estatisticaGlobal.getTotalAcertos();
    }
    public int getEstatisticaTotalErros(){
        return estatisticaGlobal.getTotalErros();
    }

    public void atualizarEstatistica(){
        estatisticaGlobal.atualizarEstatisticaJogador();
    }


    /*
        MÉTODOS PARA GERENCIADORANCO
     */
    public Map<String, List<Questao>> getGerenciadorBancoQuestoes() {
        return gerenciadorBanco.getGerenciadorBancoQuestoes();
    }

    public void adicionarQuestaoBancoQuestao(Questao questao) {
        gerenciadorBanco.adicionarQuestao(questao);
        System.out.println("Passou na model adicionar questoes");
    }

    public List<Questao> getBancoQuestoesAleatoria(int qntd) {
        return gerenciadorBanco.obterQuestoesAleatoria(qntd);
    }

    public List<Questao> getBancoQuestoesPersonalizada(int qntd, Dificuldade dificuldade, Assunto assunto) {
        return gerenciadorBanco.obterQuestoesPersonalizada(qntd, dificuldade, assunto);
    }

    /*
            MÉTODOS PARA DEBUGWIN
     */
    public List<Questao> getDebugWinQuestoes(){
        return debugWin.getQuestoes();
    }
    public void setDebugWinQuestoes(List<Questao> questoes) {
        debugWin.setQuestoes(questoes);
    }

    public Map<String, Integer> getDebugWinAcertosPorAssunto() {
        return debugWin.getAuxAcertosPorAssunto();
    }
    public Map<String, Integer> getDebugWinErrosPorAssunto() {
        return debugWin.getAuxErrosPorAssunto();
    }

    public int getDebugWinPontuacao() {
        return debugWin.getPontuacao();
    }

    public int getDebugWinMaiorSequenciaAcerto() {
        return debugWin.getMaiorSequenciaAcerto();
    }

    public Questao getDebugWinQuestaoAtual(){
        return debugWin.getQuestaoAtual();
    }
    public boolean debugWinTemMaisQuestao(){
        return debugWin.temMaisQuestao();
    }

    public boolean debugWinResponderQuestao(int resposta){
        return debugWin.responderQuestao(resposta);
    }

    public void iniciarDebugWinAleatorio() {
        List<Questao> questoes = gerenciadorBanco.obterQuestoesAleatoria(configuracoes.getQntdQuestoesPorJogo());
        debugWin = new DebugWin(questoes, configuracoes);
    }

    public void iniciarJogoPersonalizado(Dificuldade dificuldade, Assunto assunto, int qntd) {
        List<Questao> questoes = gerenciadorBanco.obterQuestoesPersonalizada(qntd, dificuldade, assunto);
        debugWin = new DebugWin(questoes, configuracoes);
    }

    public void finalizarDebugWin() {
        if (debugWin == null) return;

        setEstatisticaMaiorPontuacao(getDebugWinPontuacao());
        estatisticaContabilizarAcertosAssuntos(getDebugWinAcertosPorAssunto());
        estatisticaContabilizarErrosAssuntos(getDebugWinErrosPorAssunto());
        setMaiorSequenciaAcerto(getDebugWinMaiorSequenciaAcerto());
        atualizarEstatistica();

        encerrarJogo();
    }

    public void encerrarJogo() {
        debugWin = null;
    }






    /*
           METODOS PARA OS OBSERVADORES
     */
    public void adicionarObservador(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println();
        }
    }

    public void removerObservador(Observer observer) {
        observers.remove(observer);

    }

    private void notificarObservadores() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
