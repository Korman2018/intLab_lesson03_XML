package task01.converter;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonXmlConverterByGson<T> {
    private T object;
    private JAXBContext context;
    private Class<T> clazz;

    public JsonXmlConverterByGson(Class<T> clazz) {
        try {
            context = JAXBContext.newInstance(clazz);
            this.clazz = clazz;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String convertXmlStringToJsonString(String xmlString) {
        object = convertXMLStringToObjectByJAXB(xmlString);
        return convertObjectToJsonStringByGson(object);
    }

    public String convertJsonStringToXmlString(String jsonString) {
        object = convertJsonStringToObjectByGson(jsonString);
        return convertObjectToXMLStringByJAXB(object);
    }

    private T convertJsonStringToObjectByGson(String jsonString) {
        return new Gson().fromJson(jsonString, clazz);
    }

    private String convertObjectToJsonStringByGson(T store) {
        return new Gson().toJson(store);
    }

    private String convertObjectToXMLStringByJAXB(T store) {
        try {
            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "store.xsd");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(store, writer);

            return writer.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    private T convertXMLStringToObjectByJAXB(String xmlString) {
        try {
            StringReader reader = new StringReader(xmlString);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (T) unmarshaller.unmarshal(reader);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
