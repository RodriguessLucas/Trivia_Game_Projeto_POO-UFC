package projeto.projeto_poo.repository;

import projeto.projeto_poo.model.Assunto;
import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Questao;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class GerenciadorBanco {
    private static final String XML_PATH = "src/main/resources/projeto/projeto_poo/data/questions.xml";
    private static final int DEFAULT_SHUFFLE_COUNT = 5;

    private static Map<String, List<Questao>> bancoQuestoes;

    public GerenciadorBanco() {
        bancoQuestoes = new HashMap<>();
        inicializarQuestoesDoXml();
    }

    public Map<String, List<Questao>> getBancoQuestoes() {
        return bancoQuestoes;
    }

    public void adicionarQuestao(Questao questao) {
        bancoQuestoes.putIfAbsent(questao.getAssunto(), new ArrayList<>());
        bancoQuestoes.get(questao.getAssunto()).add(questao);
    }

    public List<Questao> obterQuestoesAleatorias(int quantidade) {
        Map<String, List<Questao>> bancoEmbaralhado = embaralharQuestoes(DEFAULT_SHUFFLE_COUNT, bancoQuestoes);
        Random random = new Random();
        List<Questao> questoesSelecionadas = new ArrayList<>();

        while (questoesSelecionadas.size() < quantidade) {
            String assuntoAleatorio = Assunto.getAssuntoAleatorio().getDescricao();
            List<Questao> questoesDoAssunto = bancoEmbaralhado.get(assuntoAleatorio);
            if (questoesDoAssunto == null || questoesDoAssunto.isEmpty()) continue;

            Questao questaoAleatoria = questoesDoAssunto.get(random.nextInt(questoesDoAssunto.size()));
            if (!questoesSelecionadas.contains(questaoAleatoria)) {
                questoesSelecionadas.add(questaoAleatoria);
            }
        }
        return questoesSelecionadas;
    }

    public List<Questao> obterQuestoesPersonalizadas(int quantidade, Dificuldade dificuldade, Assunto assunto) {
        List<Questao> questoesFiltradas = bancoQuestoes.getOrDefault(assunto.getDescricao(), new ArrayList<>())
                .stream().filter(q -> q.getDificuldade().equals(dificuldade))
                .toList();

        Collections.shuffle(questoesFiltradas);
        return questoesFiltradas.subList(0, Math.min(quantidade, questoesFiltradas.size()));
    }

    private void inicializarQuestoesDoXml() {
        bancoQuestoes.clear();
        try {
            Document doc = carregarDocumentoXml(XML_PATH);
            NodeList quizNodes = doc.getElementsByTagName("quiz");

            for (int i = 0; i < quizNodes.getLength(); i++) {
                Node node = quizNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Questao questao = criarQuestaoAPartirDeElemento((Element) node);
                    bancoQuestoes.putIfAbsent(questao.getAssunto(), new ArrayList<>());
                    bancoQuestoes.get(questao.getAssunto()).add(questao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document carregarDocumentoXml(String caminhoArquivo) throws Exception {
        File file = new File(caminhoArquivo);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    private Questao criarQuestaoAPartirDeElemento(Element element) {
        String assunto = element.getElementsByTagName("categoria").item(0).getTextContent();
        String dificuldadeStr = element.getElementsByTagName("dificuldade").item(0).getTextContent();
        String pergunta = element.getElementsByTagName("pergunta").item(0).getTextContent();
        int correta = Math.max(1, Integer.parseInt(element.getElementsByTagName("correta").item(0).getTextContent()));
        Dificuldade dificuldade = Dificuldade.fromDescricao(dificuldadeStr);

        NodeList alternativasNodes = element.getElementsByTagName("alternativa");
        List<String> alternativas = new ArrayList<>();
        for (int i = 0; i < alternativasNodes.getLength(); i++) {
            alternativas.add(alternativasNodes.item(i).getTextContent());
        }

        return new Questao(pergunta, alternativas, correta, dificuldade, assunto);
    }

    private Map<String, List<Questao>> embaralharQuestoes(int quantidade, Map<String, List<Questao>> banco) {
        Random random = new Random();
        Map<String, List<Questao>> bancoEmbaralhado = new HashMap<>();

        for (Map.Entry<String, List<Questao>> entry : banco.entrySet()) {
            List<Questao> listaQuestoes = new ArrayList<>(entry.getValue());
            for (int i = 0; i < quantidade; i++) {
                Collections.shuffle(listaQuestoes, random);
            }
            bancoEmbaralhado.put(entry.getKey(), listaQuestoes);
        }
        return bancoEmbaralhado;
    }
}