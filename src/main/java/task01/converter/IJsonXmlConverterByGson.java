package task01.converter;

public interface IJsonXmlConverterByGson {
    String convertXmlStringToJsonString(String xmlString);

    String convertJsonStringToXmlString(String jsonString);
}
