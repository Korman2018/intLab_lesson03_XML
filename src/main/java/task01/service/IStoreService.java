package task01.service;

import task01.model.Category;

import java.io.File;
import java.util.List;

public interface IStoreService {
    void putStringDataToFile(String string, String fileName);

    String getStringDataFromFile(String fileName);

    List<Category> getCategoryListFromXMLFileByStAX(String filename);

    boolean validateXMLFileByXSDFile(File xml, File xsd);
}
