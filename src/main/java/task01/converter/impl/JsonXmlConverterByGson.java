package task01.converter.impl;

import com.google.gson.Gson;
import task01.converter.IJsonXmlConverterByGson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonXmlConverterByGson<T> implements IJsonXmlConverterByGson {
    private T object;
    private JAXBContext context;
    private Class<T> clazz;
    private String shemeName;

    public JsonXmlConverterByGson(Class<T> clazz) {
        this(clazz, "");
    }

    public JsonXmlConverterByGson(Class<T> clazz, String shemeName) {
        try {
            context = JAXBContext.newInstance(clazz);
            this.clazz = clazz;
            this.shemeName = shemeName;
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
            if (!shemeName.isEmpty()) {
                marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, shemeName);
            }
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
