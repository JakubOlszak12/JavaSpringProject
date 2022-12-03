package com.example.projekt.seeders;

import com.example.projekt.dao.ILaureateRepository;
import com.example.projekt.dao.INobelPrizeRepository;
import com.example.projekt.entities.Laureate;
import com.example.projekt.entities.NobelPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Component
public class PrizeLoader implements ApplicationRunner {

    private INobelPrizeRepository nobelPrizeRepository;
    private ILaureateRepository laureateRepository;

    @Autowired
    public PrizeLoader(INobelPrizeRepository iNobelPrizeRepository, ILaureateRepository laureateRepository){
        this.nobelPrizeRepository = iNobelPrizeRepository;
        this.laureateRepository = laureateRepository;
    }

    public void run(ApplicationArguments args){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("src/main/java/com/example/projekt/seeders/nobelPrizes.xml"));
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("nobelPrizes");
            NobelPrize nobelPrize;
            for(int i = 0; i < list.getLength();i++){
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){

                    Element element = (Element) node;
                    int awardYear = Integer.parseInt(element.getElementsByTagName("awardYear").item(0).getTextContent());
                    String category = element.getElementsByTagName("en").item(0).getTextContent();
                    int prize = Integer.parseInt(element.getElementsByTagName("prizeAmount").item(0).getTextContent());
                    int prizeAdjusted = Integer.parseInt(element.getElementsByTagName("prizeAmountAdjusted").item(0).getTextContent());
                    if(element.getElementsByTagName("id").item(0) == null){
                        continue;
                    }else{
                        NodeList laureates = element.getElementsByTagName("laureates");
                        for(int j = 0; j<laureates.getLength();j++){
                            nobelPrize = new NobelPrize();
                            Node newNode = laureates.item(j);
                            if(node.getNodeType() == Node.ELEMENT_NODE){
                                Element newElement = (Element) newNode;
                                int x = Integer.parseInt(newElement.getElementsByTagName("id").item(0).getTextContent());
                                Optional<Laureate> laureate = laureateRepository.findById((long) x);
                                if(laureate.isPresent()){
                                    Laureate current = laureate.get();
                                    nobelPrize.setLaureate(current);
                                    nobelPrize.setPrize(prize);
                                    nobelPrize.setPrizeAdjusted(prizeAdjusted);
                                    nobelPrize.setAwardYear(awardYear);
                                    nobelPrize.setCategory(category);
                                    Node motivations = newElement.getElementsByTagName("motivation").item(0);
                                    Element motivation = (Element) motivations;
                                    String finalMotivation = motivation.getElementsByTagName("en").item(0).getTextContent();
                                    nobelPrize.setMotivation(finalMotivation);
                                    nobelPrizeRepository.save(nobelPrize);
                                }
                            }
                        }





                        //nobelPrize.setLaureate(laureate);
                    }

                   // System.out.println(nobelPrize);
                }

            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
