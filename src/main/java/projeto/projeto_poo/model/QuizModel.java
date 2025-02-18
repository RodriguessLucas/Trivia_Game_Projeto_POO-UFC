package projeto.projeto_poo.model;

import projeto.projeto_poo.repository.GerenciadorBanco;

public class QuizModel {
    private static final int INDICE_INICIAL = 1; // Ajusta o índice inicial como constante

    private Jogador jogador;
    private Configuracoes configuracoes;
    private GerenciadorBanco banco;
    private int indiceAtual;

    // Construtor padrão
    public QuizModel() {
        this(new GerenciadorBanco(), new Jogador(), new Configuracoes()); // Chama o construtor principal
    }

    // Construtor adicional para injeção de dependências
    public QuizModel(GerenciadorBanco banco, Jogador jogador, Configuracoes configuracoes) {
        this.banco = banco;
        this.jogador = jogador;
        this.configuracoes = configuracoes;
        this.indiceAtual = INDICE_INICIAL;
    }

    // Getter e setter do jogador
    public Jogador getJogador() {
        return jogador;
    }

    public void setNomeJogador(String nome) {
        jogador.setNome(nome);
    }

    // Getter e atualização de configurações
    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public void atualizarConfiguracoes(Configuracoes novas) {
        configuracoes = novas; // Atualiza com o novo objeto
    }

    // Métodos relacionados ao quiz
    public void resetarQuiz() {
        indiceAtual = INDICE_INICIAL; // Reseta o índice para o valor inicial
    }

    // Método consolidando lógica de gestão de observadores
    public void gerenciarObservadores(String acao) {
        switch (acao.toLowerCase()) {
            case "adicionar":
            case "attach":
                // Log ou implementação futura para adicionar observadores
                break;
            case "notificar":
            case "update":
                // Log ou implementação futura para atualizar observadores
                break;
            default:
                throw new IllegalArgumentException("Ação inválida para gerenciamento de observadores: " + acao);
        }
    }
}