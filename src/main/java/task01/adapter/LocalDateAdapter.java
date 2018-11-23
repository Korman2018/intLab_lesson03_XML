package task01.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public LocalDate unmarshal(String localDateString) {
        return LocalDate.parse(localDateString, FORMATTER);
    }

    public String marshal(LocalDate localDate) {
        return localDate.format(FORMATTER);
    }
}
