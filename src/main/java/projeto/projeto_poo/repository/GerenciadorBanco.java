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
    private static Map<String, List<Questao>> bancoQuestoes = new HashMap<>();


    public static void carregarQuestoes(){
        bancoQuestoes.clear();

        try {
            File file = new File(XML_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList quizList = doc.getElementsByTagName("quiz");

            for (int i = 0; i < quizList.getLength(); i++) {
                Node node = quizList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String assunto = element.getElementsByTagName("categoria").item(0).getTextContent();
                    String dificuldadeStr = element.getElementsByTagName("dificuldade").item(0).getTextContent();
                    String pergunta = element.getElementsByTagName("pergunta").item(0).getTextContent();
                    int correta = Integer.parseInt(element.getElementsByTagName("correta").item(0).getTextContent());

                    Dificuldade dificuldade = Dificuldade.fromDescricao(dificuldadeStr);

                    NodeList alternativaNodes = element.getElementsByTagName("alternativa");
                    List<String> alternativas = new ArrayList<>();
                    for (int j = 0; j < alternativaNodes.getLength(); j++) {
                        alternativas.add(alternativaNodes.item(j).getTextContent());
                    }

                    Questao questao = new Questao(pergunta, alternativas, correta, dificuldade.getDescricao(), assunto);

                    // Adicionando ao HashMap
                    bancoQuestoes.putIfAbsent(assunto, new ArrayList<>());
                    bancoQuestoes.get(assunto).add(questao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<Questao>> embaralharQuestoes(int quantidade, Map<String, List<Questao>> aux) {

        while(quantidade != 0) {
            Random rand = new Random();
            for (List<Questao> listaQuestoes : aux.values()) {
                Collections.shuffle(listaQuestoes, rand);
            }
            quantidade--;
        }
        return aux;
    }

    public static void adicionarQuestao(Questao questao) {
        bancoQuestoes.putIfAbsent(questao.getAssunto(), new ArrayList<>());
        bancoQuestoes.get(questao.getAssunto()).add(questao);
    }

    public static List<Questao> obterQuestoesAleatoria(int quantidade) {
        Map<String, List<Questao>> auxBancoQuestoes = bancoQuestoes;
        embaralharQuestoes(5, auxBancoQuestoes);

        Random rand = new Random();
        ArrayList<Questao> questoesEscolhidas = new ArrayList<>();

        for(int i = 0; i < quantidade; i++) {
            boolean questaoNaoRepetida = true;
            do {
                String assuntoAux = Assunto.getAssuntoAleatorio().getDescricao();
                Dificuldade dificuldadeAux = Dificuldade.getDificuldadeAleatoria();
                Questao questaoAux = auxBancoQuestoes.get(assuntoAux).get(rand.nextInt(auxBancoQuestoes.size()));


                if(questaoAux.getDificuldade().equals(dificuldadeAux)) {
                    if (!questoesEscolhidas.contains(questaoAux)) {
                        questoesEscolhidas.add(questaoAux);
                        questaoNaoRepetida = false;
                    }
                }
            } while (questaoNaoRepetida);
        }
        return questoesEscolhidas;
    }

    public static List<Questao> obterQuestoesPersonalizada(int quantidade, Dificuldade dificuldade, Assunto assunto) {
        ArrayList<Questao> auxListaQuestaoPorDificuldade = new ArrayList<>();
        ArrayList<Questao> questoesPersonalizada = new ArrayList<>();

        int auxTam = bancoQuestoes.get(assunto.getDescricao()).size();
        for(int i = 0; i<auxTam; i++) {
            Questao aux = bancoQuestoes.get(assunto.getDescricao()).get(i);
            if(aux.getDificuldade().equals(dificuldade)) {
                auxListaQuestaoPorDificuldade.add(aux);
            }
        }
        Collections.shuffle(auxListaQuestaoPorDificuldade);
        for(int i = 0; i < quantidade; i++) {
            questoesPersonalizada.add(auxListaQuestaoPorDificuldade.get(i));
        }
        return questoesPersonalizada;
    }

    public static void imprimirQuestoes() {
        for (Map.Entry<String, List<Questao>> entry : bancoQuestoes.entrySet()) {
            System.out.println("Categoria: " + entry.getKey());
            for (Questao questao : entry.getValue()) {
                System.out.println("  Pergunta: " + questao.getPergunta());
                System.out.println("  Dificuldade: " + questao.getDificuldade().getDescricao());
                System.out.println("  Alternativas: " + questao.getAlternativas());
                System.out.println("  Resposta Correta: " + questao.getCorreta());
                System.out.println();
            }
        }
    }

}

/*
depois arumar uma forma de melhorar a escoha das questoes pseudoaleatoria, pois desta forma nao é mt ineficiente
 */

