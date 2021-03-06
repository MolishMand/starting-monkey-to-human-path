package RPIS41.Olukhov.wdad.learn.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by molish on 27.09.2016.
 */
public class XmlTask {

    private Document xmlFile;
    private String path;

    public XmlTask(String path) throws IOException, SAXException, ParserConfigurationException {
        this.path = path;
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        xmlFile = builder.parse(file);
    }

    public int earningsTotal(String officiantSecondName, Calendar calendar){
        int result = 0;

        NodeList dates = xmlFile.getElementsByTagName("date");
        for(int i = 0 ; i < dates.getLength(); i++){
            Element date = (Element)dates.item(i);
            if(Integer.parseInt(date.getAttribute("day")) == calendar.get(Calendar.DAY_OF_MONTH) &&
                Integer.parseInt(date.getAttribute("month")) == (calendar.get(Calendar.MONTH) + 1) &&
                Integer.parseInt(date.getAttribute("year")) == calendar.get(Calendar.YEAR)){
                NodeList orders = date.getElementsByTagName("order");
                for(int j = 0; j < orders.getLength(); j++){
                    Element order = (Element)orders.item(j);
                    Element officicant = (Element)order.getElementsByTagName("officiant").item(0);
                        if(officicant.getAttribute("secondname").equals(officiantSecondName)) {
                            int orderTotalPrice = 0;
                            NodeList items = order.getElementsByTagName("item");
                            for (int k = 0; k < items.getLength(); k++) {
                                Element currentItem = (Element)items.item(k);
                                orderTotalPrice += Integer.parseInt(currentItem.getAttribute("cost"));
                            }
                            result+=orderTotalPrice;
                            NodeList totalcost = order.getElementsByTagName("totalcost");
                            if (totalcost.getLength() < 1) {
                                Element totCost = xmlFile.createElement("totalcost");
                                totCost.appendChild(xmlFile.createTextNode(Integer.toString(orderTotalPrice)));
                                order.appendChild(totCost);
                                saveChanges();
                            } else if (Integer.parseInt(totalcost.item(0).getTextContent()) != orderTotalPrice) {
                                totalcost.item(0).setTextContent(Integer.toString(orderTotalPrice));
                                saveChanges();
                            }
                        }
                    }
                }
            }

        return result;
    }
    public void removeDay(Calendar calendar){
        NodeList dates = xmlFile.getElementsByTagName("date");
        for(int i = 0 ; i < dates.getLength(); i++){
            Element date = (Element)dates.item(i);
            if( Integer.parseInt(date.getAttribute("day")) == calendar.get(Calendar.DAY_OF_MONTH) &&
                Integer.parseInt(date.getAttribute("month")) == (calendar.get(Calendar.MONTH) + 1) &&
                Integer.parseInt(date.getAttribute("year")) == calendar.get(Calendar.YEAR)){
                xmlFile.getDocumentElement().removeChild(date);
                saveChanges();
                return;
            }
        }
    }
    public void changeOfficiantName(String oldFirstName, String oldSecondName,String newFirstName, String newSecondName){
        NodeList dates = xmlFile.getElementsByTagName("date");
        for(int i = 0 ; i < dates.getLength(); i++){
            Element date = (Element)dates.item(i);
            NodeList orders = date.getElementsByTagName("order");
            for(int j = 0; j < orders.getLength(); j++){
                Element order = (Element)orders.item(j);
                Element officiant = (Element)order.getElementsByTagName("officiant").item(0);
                if( officiant.getAttribute("firstname") != null &&
                    officiant.getAttribute("firstname").equals(oldFirstName) &&
                    officiant.getAttribute("secondname").equals(oldSecondName)) {
                    officiant.setAttribute("firstname", newFirstName);
                    officiant.setAttribute("secondname", newSecondName);
                    saveChanges();
                }
            }
        }
    }
    public String getLuckyGuy(Calendar calendar){
        int maxItemCost = 0;
        String luckyGuySecondName = null;
            NodeList dates = xmlFile.getElementsByTagName("date");
            for(int i = 0; i < dates.getLength(); i++){
                Element date = (Element)dates.item(i);
                if(Integer.parseInt(date.getAttribute("day")) == calendar.get(Calendar.DAY_OF_MONTH) &&
                        Integer.parseInt(date.getAttribute("month")) == (calendar.get(Calendar.MONTH) + 1) &&
                        Integer.parseInt(date.getAttribute("year")) == calendar.get(Calendar.YEAR)){
                    NodeList orders = date.getElementsByTagName("order");
                    for(int j = 0; j < orders.getLength(); j++){
                        Element order = (Element) orders.item(j);
                        NodeList items = order.getElementsByTagName("item");
                        int maxOrderItemCost = 0;
                        for(int k = 0; k < items.getLength(); k++){
                            Element item = (Element)items.item(k);
                            int curentItemCost = Integer.parseInt(item.getAttribute("cost"));
                            if(maxOrderItemCost < curentItemCost)
                                maxOrderItemCost = curentItemCost;
                        }
                        if(maxItemCost < maxOrderItemCost){
                            maxItemCost = maxOrderItemCost;
                            Element officiant = (Element)order.getElementsByTagName("officiant").item(0);
                            luckyGuySecondName = officiant.getAttribute("secondname");
                        }
                    }
                }
            }
        return luckyGuySecondName;
    }

    private void saveChanges(){
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(xmlFile);
            StreamResult streamResult = new StreamResult(new File(path));
            if(xmlFile.getDoctype() == null){
                DOMImplementation domImpl = xmlFile.getImplementation();
                DocumentType doctype = domImpl.createDocumentType("restaurant", "", "restaurant");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            }else{
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, xmlFile.getDoctype().getSystemId());
            }
            transformer.transform(domSource, streamResult);
        }
        catch (TransformerConfigurationException e){e.printStackTrace();}
        catch (TransformerException e){e.printStackTrace();}
    }

}
