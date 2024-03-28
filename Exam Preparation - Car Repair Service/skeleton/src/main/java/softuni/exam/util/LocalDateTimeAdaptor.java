package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdaptor extends XmlAdapter<String, LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime unmarshal(String value) throws Exception {
        return LocalDateTime.parse(value, formatter);
    }

    @Override
    public String marshal(LocalDateTime value) throws Exception {
        return value.format(formatter);
    }
}
