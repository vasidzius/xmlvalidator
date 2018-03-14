package com.vasidzius.xmlvalidator.model;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

class AbstractXmlHandler {

    private final static Logger LOGGER = Logger.getLogger(AbstractXmlHandler.class);

    void parse(SAXParser parser, DefaultHandler handler, String xml) {
        try {
            parser.parse(xml, handler);
        } catch (SAXException | IOException e) {
            String message = "Error during parsing occurred";
            LOGGER.info(message, e);
            throw new RuntimeException(message, e);
        }
    }
}
