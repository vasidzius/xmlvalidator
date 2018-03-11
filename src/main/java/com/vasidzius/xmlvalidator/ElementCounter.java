package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

class ElementCounter {

    private final static Logger LOGGER = Logger.getLogger(ElementCounter.class);

    private String xml;
    private String elementName;

    ElementCounter(String xmlUri, String elementName) {
        this.elementName = elementName;
        this.xml = xmlUri;
    }

    int count() {
        SAXParser saxParser = getSAXParser();
        final int[] count = {0};
        DefaultHandler handler = getHandler(count);
        parse(saxParser, handler);
        return count[0];
    }

    private DefaultHandler getHandler(int[] count) {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equals(elementName)) {
                    count[0]++;
                }
            }
        };
    }

    private void parse(SAXParser saxParser, DefaultHandler handler) {
        try {
            saxParser.parse(xml, handler);
        } catch (SAXException | IOException e) {
            LOGGER.info("Error during parsing occurred", e);
        }
    }

    private SAXParser getSAXParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.debug("Parser can't be created", e);
            e.printStackTrace();
        }
        return saxParser;
    }
}
