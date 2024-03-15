package bg.softuni._xmlexercise.carDealer.utils;

import jakarta.xml.bind.JAXBException;

import java.nio.file.Path;

public interface XmlParser {
    <E> E parse(Class<E> clazz, String path) throws JAXBException;
    <E> void writeToFile(Class<E> clazz,E object, String path) throws JAXBException;
}
