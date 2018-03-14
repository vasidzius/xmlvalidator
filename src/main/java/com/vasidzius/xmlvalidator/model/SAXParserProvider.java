package com.vasidzius.xmlvalidator.model;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXParserProvider {

    private final static Logger LOGGER = Logger.getLogger(SAXParserProvider.class);

    SAXParser getParser(boolean validating, boolean namespaceAware) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(validating);
        factory.setNamespaceAware(namespaceAware);
        try {
            return factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            String message = "Parser can't be created";
            LOGGER.debug(message, e);
            throw new RuntimeException(message, e);
        }
    }

}
