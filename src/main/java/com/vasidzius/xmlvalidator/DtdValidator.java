package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

class DtdValidator {
    private final static Logger LOGGER = Logger.getLogger(DtdValidator.class);

    private String xml;

    DtdValidator(String xml) {
        this.xml = xml;
    }

    boolean dtdValidate() {
        CustomErrorHandler handler = new CustomErrorHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setErrorHandler(handler);
            reader.parse(new InputSource(xml));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.info("Error during parsing occurred", e);
            System.exit(-1);
        }
        return handler.isValidationSuccess();
    }
}
