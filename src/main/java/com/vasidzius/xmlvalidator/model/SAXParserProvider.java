package com.vasidzius.xmlvalidator.model;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserProvider {

    SAXParser getParser(boolean validating, boolean namespaceAware){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(validating);
        factory.setNamespaceAware(namespaceAware);
        try {
            return factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            String message = "Parser can't be created";
            throw new RuntimeException(message, e);
        }
    }

}
