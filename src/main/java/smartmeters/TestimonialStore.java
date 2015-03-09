package smartmeters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by nikolay on 19.02.15.
 */

public class TestimonialStore {

    private List<IListener> listeners = new ArrayList<IListener>();
    private static Map<String, String> data = new HashMap<String,String>();
    private static TestimonialStore instance = new TestimonialStore();
    protected BuildingStore buildings = BuildingStore.getInstance();

    public TestimonialStore() {}
    public static TestimonialStore getInstance() {
        return instance;
    }

    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    public String getData() {
        String s = "";

        for(String key: data.keySet()) {
            s += getData(key) + "\n";
        }

        return s;
    }
    public String getData(String id) {
        String s = "";
        Building b = buildings.getBuilding(Integer.parseInt(id));
        if (data.containsKey(id)) {

        s = "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n" +
            "@prefix meter: <http://purl.org/NET/ssnext/meters/core#>\n" +
            "\n" +
            "<#temperature-[meter ID]-[timestamp]> a hmtr:TemperatureObservation ;\n" +
            "    ssn:observationResultTime “2010-02-23T10:00:21”^^xsd:dateTime ;\n" +
            "    ssn:observedBy <meter URI> ;\n" +
            "    ssn:observationResult <#temperature-[meter ID]-[timestamp]-result> .\n" +
            "\n" +
            "<#temperature-[meter ID]-[timestamp]-results> a hmtr:TemperatureSensorOutput ;\n" +
            "    ssn:isProducedBy <meter URI> ;\n" +
            "    ssn:hasValue <#temperature-[meter ID]-[timestamp]-resultvalue> .\n" +
            "\n" +
            "<#temperature-[meter ID]-[timestamp]-resultvalue> a hmtr:TemperatureValue ;\n" +
            "    meter:hasQuantityValue “80”^^xsd:float";
/*
            s  = "Адрес - " + b.getAddress() + ", ";
            s += "квартир - " + Integer.toString(b.getQuarters()) + ", ";
            s += "потребляемое тепло - " + data.get(id) + " Гкал";
*/
        }
        System.out.println(s);
        return s;
    }

    public void setData(String _heat, String _id) {
        if (!data.containsKey(_id)) {
            for (IListener l : listeners) {
                l.onCreated(_id);
            }
        }
        data.put(_id, _heat);
        for (IListener l : listeners) {
            l.onUpdated(_id, _heat);
        }
       // observable.notifyObservers(new Notification(this, "1"));
    }
}
