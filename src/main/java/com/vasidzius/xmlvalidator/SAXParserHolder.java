package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParserHolder {

    private final static Logger LOGGER = Logger.getLogger(SAXParserHolder.class);
    private SAXParser parser;

    SAXParserHolder(boolean validating, boolean namespaceAware) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(validating);
        factory.setNamespaceAware(namespaceAware);
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.debug("Parser can't be created", e);
            System.exit(-1);
        }
    }

    void parse(DefaultHandler handler, String xml) {
        try {
            parser.parse(xml, handler);
        } catch (SAXException | IOException e) {
            LOGGER.info("Error during parsing occurred", e);
            System.exit(-1);
        }
    }
}
