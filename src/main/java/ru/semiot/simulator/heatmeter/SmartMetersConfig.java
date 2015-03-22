package ru.semiot.simulator.heatmeter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class SmartMetersConfig {

    private final Map<String, String> data;
    public static final SmartMetersConfig conf = new SmartMetersConfig();

    private SmartMetersConfig() {
        this.data = new HashMap<>();

        data.put("metersCount", Integer.toString(3));
        data.put("metersHeartbeat", Integer.toString(10000));
        data.put("outsideTemperature", Integer.toString(3));
        data.put("quartersMax", Integer.toString(200));
        data.put("quartersMin", Integer.toString(80));
        data.put("timeToStart", Integer.toString(10));
        data.put("startPort", Integer.toString(60000));
        data.put("registerURI", "coap://localhost:3131/register");
    }

    public void setConfigFromFile(String filename) {
        try {
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            for (String i : data.keySet()) {
                data.put(i, doc.getElementsByTagName(i).item(0).getTextContent());
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }

    public int getMetersCount() {
        return Integer.parseInt(data.get("metersCount"));
    }

    public int getMetersHeartbeat() {
        return Integer.parseInt(data.get("metersHeartbeat"));
    }

    public int getOutsideTemperature() {
        return Integer.parseInt(data.get("outsideTemperature"));
    }

    public int getQuartersMax() {
        return Integer.parseInt(data.get("quartersMax"));
    }

    public int getQuartersMin() {
        return Integer.parseInt(data.get("quartersMin"));
    }

    public int getTimeToStart() {
        return Integer.parseInt(data.get("timeToStart"));
    }

    public int getStartPort() {
        return Integer.parseInt(data.get("startPort"));
    }

    public String getRegisterURI() {
        return data.get("registerURI");
    }

}
