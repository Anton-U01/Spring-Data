package softuni.exam.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeAdaptor extends XmlAdapter<String, LocalTime> {
    @Override
    public LocalTime unmarshal(String string) throws Exception {
        return LocalTime.parse(string);
    }

    @Override
    public String marshal(LocalTime date) throws Exception {
        return date.toString();
    }
}
