package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalTimeAdaptor extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String string) throws Exception {
        return LocalDate.parse(string);
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.toString();
    }
}
