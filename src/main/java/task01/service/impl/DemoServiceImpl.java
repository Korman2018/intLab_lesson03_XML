package task01.service.impl;

import task01.converter.JsonXmlConverterByGson;
import task01.model.Store;
import task01.service.IDemoservice;
import task01.service.IStoreService;

import java.io.File;

public class DemoServiceImpl implements IDemoservice {
    private static final String SOURCE_XML_FILENAME = "source.xml";
    private static final String XSD_FILENAME = "store.xsd";
    private static final String JSON_FILENAME = "store.json";
    private static final String CONVERTED_XML_FILENAME = "converted.xml";
    private static final JsonXmlConverterByGson<Store> CONVERTER = new JsonXmlConverterByGson<>(Store.class);

    private IStoreService storeService;

    public DemoServiceImpl() {
        storeService = new StoreServiceImpl();
    }

    @Override
    public void runDemo() {
        if (isXmlValid(SOURCE_XML_FILENAME, XSD_FILENAME)) {
            System.out.println(SOURCE_XML_FILENAME + " is valid");

            System.out.println("\nGet Store object from " + SOURCE_XML_FILENAME + "\n");
            Store storeFromSourceXML = new Store(storeService.getCategoryListFromXMLFileByStAX(SOURCE_XML_FILENAME));
            System.out.println(storeFromSourceXML);

            System.out.println("\nConvert XML file:" + SOURCE_XML_FILENAME + " to JSON file:" + JSON_FILENAME);
            String xmlStringFromSourceFile = storeService.getStringDataFromFile(SOURCE_XML_FILENAME);
            String jsonFromXml = CONVERTER.convertXmlStringToJsonString(xmlStringFromSourceFile);
            storeService.putStringDataToFile(jsonFromXml, JSON_FILENAME);

            System.out.println("\nConvert JSON file:" + JSON_FILENAME + " to XML file:" + CONVERTED_XML_FILENAME);
            String xmlFromJson = CONVERTER.convertJsonStringToXmlString(jsonFromXml);
            storeService.putStringDataToFile(xmlFromJson, CONVERTED_XML_FILENAME);

            System.out.println();
            System.out.println(CONVERTED_XML_FILENAME + " is valid? " + isXmlValid(CONVERTED_XML_FILENAME, XSD_FILENAME));
        } else {
            System.out.println(SOURCE_XML_FILENAME + " is not valid");
        }
    }

    private boolean isXmlValid(String xmlFileName, String xsdFileName) {
        return storeService.validateXMLFileByXSDFile(new File(xmlFileName), new File(xsdFileName));
    }
}