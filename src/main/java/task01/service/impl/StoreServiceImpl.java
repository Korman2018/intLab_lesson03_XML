package task01.service.impl;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;
import task01.model.Category;
import task01.service.IStoreService;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreServiceImpl implements IStoreService {

    public void putStringDataToFile(String string, String fileName) {
        try {
            FileUtils.writeStringToFile(new File(fileName), string, "UTF-8", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringDataFromFile(String fileName) {
        try {
            return FileUtils.readFileToString(new File(fileName), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<Category> getCategoryListFromXMLFileByStAX(String filename) {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            StreamSource xmlSource = new StreamSource(filename);
            XMLStreamReader streamReader;
            List<Category> categories = new ArrayList<>();
            JAXBContext context = JAXBContext.newInstance(Category.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            streamReader = factory.createXMLStreamReader(xmlSource);

            do {
                streamReader.nextTag();

                if ("store".equals(streamReader.getLocalName()) && streamReader.isEndElement()) {
                    break;
                }
                if ("category".equals(streamReader.getLocalName())) {
                    Category category = (Category) unmarshaller.unmarshal(streamReader);
                    categories.add(category);
                }
            } while (true);

            return categories;

        } catch (XMLStreamException | JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean validateXMLFileByXSDFile(File xml, File xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
