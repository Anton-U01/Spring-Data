package bg.softuni._xmlexercise.carDealer.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class XmlParserImpl implements XmlParser{
    @Override
    public <E> E parse(Class<E> clazz, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        E unmarshal = (E) unmarshaller.unmarshal(new File(path));
        return unmarshal;
    }

    @Override
    public <E> void writeToFile(Class<E> clazz,E object, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        marshaller.marshal(object,new File(path));
    }

}
