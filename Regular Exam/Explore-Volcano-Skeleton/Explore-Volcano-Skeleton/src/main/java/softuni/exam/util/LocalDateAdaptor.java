package softuni.exam.util;

import org.springframework.format.datetime.DateFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdaptor extends XmlAdapter<String, LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate unmarshal(String value) throws Exception {
        return LocalDate.parse(value, formatter);
    }

    @Override
    public String marshal(LocalDate value) throws Exception {
        return value.format(formatter);
    }
}
