package pasandolista.cnf;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Load extends FileManager {

    public Load(){
        
    }
    
    public String getValue() {
        String x = "";
        
         try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("save");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("value").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "0";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public boolean getAutoSave() {
        String x = "";
        
         try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("save");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("autosave").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "-1";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         boolean y = false;
         
         if(x.equals("true")){
             y = true;
         }
         
        return y;
    }

    public String getBackgroundImage() {
        String x = "";
        
         try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("background");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("image").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "0";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public String getFontColor() {
        String x = "";
        
         try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("font");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("color").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "#FFFFFF";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public String getXMLFileName() {
        String x = "";
        
         try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("save");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    x = eElement.getElementsByTagName("name").item(0).getTextContent();

                    if (x.isEmpty()) {
                        x = "./cfg/usrsample";
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

}
