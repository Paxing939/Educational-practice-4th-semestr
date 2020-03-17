package com.company.xml;

import com.company.ammunition.Ammunition;
import com.company.ammunition.Helmet;
import com.company.ammunition.Jacket;
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
import java.util.ArrayList;

public class XMLParser {

    public static ArrayList<Ammunition> parseXMLFile(String path) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Ammunition> ammunitions = new ArrayList<>();
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        NodeList nodeList = document.getElementsByTagName("Helmet");
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            ammunitions.add(new Helmet(element.getElementsByTagName("manufacturer").item(0).getTextContent(),
                    Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()),
                    Double.parseDouble(element.getElementsByTagName("weight").item(0).getTextContent()),
                    Integer.parseInt(element.getElementsByTagName("radius").item(0).getTextContent())
            ));
        }

        nodeList = document.getElementsByTagName("Jacket");
        node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            ammunitions.add(new Jacket(element.getElementsByTagName("manufacturer").item(0).getTextContent(),
                    Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()),
                    Double.parseDouble(element.getElementsByTagName("weight").item(0).getTextContent()),
                    Integer.parseInt(element.getElementsByTagName("leatherPercentage").item(0).getTextContent())
            ));
        }

        return ammunitions;
    }

}
