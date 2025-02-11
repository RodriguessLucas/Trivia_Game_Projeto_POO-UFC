package projeto.projeto_poo.repository;

import projeto.projeto_poo.model.Dificuldade;
import projeto.projeto_poo.model.Questao;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class GerenciadorBanco {
    private static final String XML_PATH = "projeto/projeto_poo/data/question.xml";

    public static List<Questao> carregarQuestoes(){
        List<Questao> listaQuestoes = new ArrayList<>();
        try{
            File file = new File(XML_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList quizList = doc.getElementsByTagName("quiz");

            for (int i = 0; i < quizList.getLength(); i++){
                Node node = quizList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    String assunto = element.getElementsByTagName("categoria").item(0).getTextContent();
                    String dificuldadeStr = element.getElementsByTagName("dificuldade").item(0).getTextContent();
                    String pergunta = element.getElementsByTagName("pergunta").item(0).getTextContent();
                    int correta = Integer.parseInt(element.getElementsByTagName("correta").item(0).getTextContent());

                    Dificuldade dificuldade = Dificuldade.valueOf(dificuldadeStr.toUpperCase());

                    NodeList alternativaNodes = element.getElementsByTagName("alternativa");
                    List<String> alternativas = new ArrayList<>();
                    for (int j = 0; j < alternativaNodes.getLength(); j++) {
                        alternativas.add(alternativaNodes.item(j).getTextContent());
                    }

                    listaQuestoes.add(new Questao(pergunta, alternativas, correta, dificuldade, assunto));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return listaQuestoes;
    }
}
