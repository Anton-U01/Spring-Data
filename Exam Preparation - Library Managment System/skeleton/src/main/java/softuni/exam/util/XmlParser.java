package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    public <E> E parse(Class<E> clazz,String path) throws JAXBException;
    <E> void writeToFile(Class<E> clazz,E object, String path) throws JAXBException;
}
